package one.beatsbot.state.handlers

import diode.{ActionHandler, ActionResult, Circuit, Effect}
import one.beatsbot.discord.DiscordAction.{DeleteMessage, SetPoints, VoteMessage}
import one.beatsbot.model._
import one.beatsbot.state.actions.{MessageDeleted, VoteAction, VoteReaction, VotingStarted}
import one.beatsbot.store.Database

import scala.concurrent.ExecutionContext.Implicits.global

trait BeatHandler { self: Circuit[BotState] =>

  def handlePlaybackEnd(playing: NowPlaying): Effect = {
    println("FINISHED")
    println(playing)
    updatePoints(playing)
    Effect.action(DeleteMessage(playing.pointsMessage))
  }

  def updatePoints(playing: NowPlaying, newVotes: Option[Map[UserRef, Vote]] = None): Int = {
    val points = playing.storedPoints.points + newVotes.getOrElse(playing.votes).values.map(_.delta).sum
    Database.updatePoints(playing.beat.id, points)
    points
  }

  val beatHandler: ActionHandler[BotState, Option[NowPlaying]] = new ActionHandler(zoomTo(_.nowPlaying)) {
    override def handle: PartialFunction[Any, ActionResult[BotState]] = {
      case VotingStarted(beat, points, playMessage, voteMessage, queuedBy) =>
        val cleanupEffect = value.map(handlePlaybackEnd)
        val newState = Some(NowPlaying(
          playMessage,
          voteMessage,
          Seq(playMessage.getId, voteMessage.getId),
          beat,
          points,
          queuedBy
        ))
        cleanupEffect match {
          case Some(effect) => updated(newState, effect)
          case _ => updated(newState)
        }
      case MessageDeleted(messageId) =>
        value.filter(_.playMessage.getId == messageId)
          .map(playing => effectOnly(handlePlaybackEnd(playing)))
          .getOrElse(noChange)
      case action: VoteAction  =>
        value.filter { nowPlaying =>
          action match {
            case VoteReaction(_, _, message) => nowPlaying.relevantMessages.contains(message)
            case _ => true
          }
        }.map { nowPlaying =>
          val newMessages = action match {
            case VoteMessage(_, _, message) => nowPlaying.relevantMessages :+ message.getId
            case _ => nowPlaying.relevantMessages
          }
          val newVotes = nowPlaying.votes.updated(action.userRef, action.vote)
          val points = updatePoints(nowPlaying, Some(newVotes))
          updated(
            Some(nowPlaying.copy(votes = newVotes, relevantMessages = newMessages)),
            Effect.action(SetPoints(nowPlaying.pointsMessage, points))
          )
        }.getOrElse(noChange)
    }
  }
}
