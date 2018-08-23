package one.beatsbot.discord

import com.mitchtalmadge.asciidata.table.ASCIITable
import diode.{Action, Effect}
import net.dv8tion.jda.core.EmbedBuilder
import one.beatsbot.model._
import net.dv8tion.jda.core.entities.{Channel, Guild, Message, TextChannel}
import one.beatsbot.state.actions.{VoteAction, VotingStarted}
import one.beatsbot.store.Database

import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.implicitConversions


sealed trait DiscordAction extends Action {
  def apply(guild: Guild): Option[Effect]
}

object DiscordAction {
  private def pointsEmbed(points: Int, footer: String) = new EmbedBuilder()
    .setFooter(footer, null)
    .setTitle(s"🎺 $points")
    .setColor(0x08d58f)
    .build()

  case class PlaybackStarted(playMessage: Message, beatName: String, beatUrl: String, queuedBy: Option[UserRef] = None) extends DiscordAction {
    override def apply(guild: Guild): Option[Effect] = {
      val footer = s"$beatName ${
        queuedBy.map(ref => s"played by ${ref.name}").getOrElse("playing")
      } on ${playMessage.getMember.getEffectiveName}"

      println(footer)
      Some(Effect(for {
        points <- Database.getOrCreateBeat(beatName, beatUrl)
        voteMessage <- DiscordService.sequenceAction(playMessage.getChannel.sendMessage(pointsEmbed(points.points, footer)))
        _ <- DiscordService.sequenceAction(voteMessage.addReaction(EmojiService.updoot))
        _ <- DiscordService.sequenceAction(voteMessage.addReaction(EmojiService.downboat))
      } yield VotingStarted(
        BeatRef(beatName, points.id),
        points,
        playMessage,
        voteMessage,
        queuedBy
      )))
    }
  }

  case class SetPoints(message: Message, newPoints: Int) extends DiscordAction {
    override def apply(guild: Guild): Option[Effect] = {
     val footer = message.getEmbeds.get(0).getFooter.getText
     DiscordService.sequenceAction(message.editMessage(pointsEmbed(newPoints, footer)))
     None
    }
  }

  case class VoteMessage(userRef: UserRef, vote: Vote, message: Message) extends DiscordAction with VoteAction {
    override def apply(guild: Guild): Option[Effect] = {
      DiscordService.sequenceAction(message.addReaction(EmojiService.randomReact(vote)))
      None
    }
  }

  case class DeleteMessage(message: Message) extends DiscordAction {
    override def apply(guild: Guild): Option[Effect] = {
      DiscordService.sequenceAction(message.delete)
      None
    }
  }

  case class ListBeats(beats: Seq[Beat], channel: TextChannel) extends DiscordAction {
    override def apply(guild: Guild): Option[Effect] = {
      val headers = Array("#", "Name", "Points")
      val data = beats.zipWithIndex.map { case (beat, i) =>
        Array((i + 1).toString, beat.name, beat.points.toString)
      }.toArray
      val text = ASCIITable.fromData(headers, data).toString
      DiscordService.sequenceAction(channel.sendMessage(s"```$text```"))
      None
    }
  }
}