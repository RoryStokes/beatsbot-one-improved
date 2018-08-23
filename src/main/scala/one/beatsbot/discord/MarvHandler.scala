package one.beatsbot.discord

import diode.Action
import net.dv8tion.jda.core.entities.Message
import one.beatsbot.discord.DiscordAction.PlaybackStarted

import scala.collection.JavaConverters._
import scala.language.implicitConversions
import scala.util.matching.Regex

object MarvHandler {

  val marvId = 234395307759108106L
  val nowPlayingTitle = "Now playing"
  val SongDescription: Regex = "\\[(.*)\\]\\((.*)\\) \\[<@([0-9]*)>\\].*".r

  def handleMessage(message: Message): Option[Action] = {
    Option(message).filter(_.getAuthor.getIdLong == marvId)
      .flatMap(_.getEmbeds.asScala.headOption)
      .filter(_.getTitle == nowPlayingTitle)
      .flatMap(embed => embed.getDescription match {
        case SongDescription(name, url, userId) => Some(name, url, userId)
        case _ => None
      })
      .map { case (name, url, userId) =>
        PlaybackStarted(message, name, url, DiscordService.userRef(userId, message.getGuild))
      }
  }
}
