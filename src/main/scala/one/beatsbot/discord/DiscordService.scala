package one.beatsbot.discord

import java.util

import one.beatsbot.model.{MessageId, UserId, UserRef}
import net.dv8tion.jda.core.entities._
import net.dv8tion.jda.core.hooks.EventListener
import net.dv8tion.jda.core.requests.RestAction
import net.dv8tion.jda.core.requests.restaction.{AuditableRestAction, ChannelAction}
import net.dv8tion.jda.core.{AccountType, JDA, JDABuilder, Permission}

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

class DiscordService(token: String, listeners: Seq[EventListener]) {
  val jda: JDA = listeners.foldLeft(new JDABuilder(AccountType.BOT)) { case (builder, listener) =>
    builder.addEventListener(listener)
  }.setToken(token).buildBlocking()

}

object DiscordService {
  def userRef(member: Member) = UserRef(member.getEffectiveName, member.getUser.getId)
  def userRef(userId: UserId, guild: Guild): Option[UserRef] = {
    Option(guild.getMemberById(userId)).map(userRef)
  }

  def sequenceAction[T](action: RestAction[T]): Future[T] = Future {
    action.complete()
  }

  def messageId(message: Message) = MessageId(message.getId, message.getChannel.getId)
  def messageFromId(messageId: MessageId, guild: Guild): Future[Message] =
    sequenceAction(guild.getTextChannelById(messageId.channel).getMessageById(messageId.message))

  val viewPermissions: util.Collection[Permission] = Seq(Permission.VIEW_CHANNEL).asJavaCollection
  val noPermissions: util.Collection[Permission] = Nil.asJavaCollection

  private def grantPermissions(channel: ChannelAction, role: Role,  permissions: Seq[Permission]) = {
    channel.addPermissionOverride(
      role, permissions.asJavaCollection, Seq[Permission]().asJavaCollection
    )
  }

  private def restrictPermissions(channel: ChannelAction, role: Role,  permissions: Seq[Permission]) = {
    channel.addPermissionOverride(
      role, Seq[Permission]().asJavaCollection, permissions.asJavaCollection
    )
  }
}