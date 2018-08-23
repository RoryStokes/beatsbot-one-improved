package one.beatsbot.state

import diode.Action
import net.dv8tion.jda.core.entities.{Guild, Message}
import one.beatsbot.model._


package object actions  {

  case class ReceivedCommand(
    commandName: String,
    from: UserRef,
    mentions: Seq[UserRef],
    content: Option[String],
    message: Message
  ) extends Action

  case class DiscordResult[T](result: T) extends Action
  case class DiscordUpdate(guild: Guild) extends Action

  case class VotingStarted(
    beat: BeatRef,
    points: Beat,
    playMessage: Message,
    voteMessage: Message,
    queuedBy: Option[UserRef]
  ) extends Action


  trait VoteAction extends Action {
    def userRef: UserRef
    def vote: Vote
  }

  case class VoteReaction(
    userRef: UserRef,
    vote: Vote,
    messageId: String
  ) extends VoteAction

  case class MessageDeleted(messageId: String) extends Action
}