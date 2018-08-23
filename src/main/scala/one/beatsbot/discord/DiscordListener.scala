package one.beatsbot.discord

import net.dv8tion.jda.client.events.call.{CallCreateEvent, CallDeleteEvent, GenericCallEvent}
import net.dv8tion.jda.client.events.call.update.{CallUpdateRegionEvent, CallUpdateRingingUsersEvent, GenericCallUpdateEvent}
import net.dv8tion.jda.client.events.call.voice._
import net.dv8tion.jda.client.events.group._
import net.dv8tion.jda.client.events.group.update.{GenericGroupUpdateEvent, GroupUpdateIconEvent, GroupUpdateNameEvent, GroupUpdateOwnerEvent}
import net.dv8tion.jda.client.events.message.group._
import net.dv8tion.jda.client.events.message.group.react.{GenericGroupMessageReactionEvent, GroupMessageReactionAddEvent, GroupMessageReactionRemoveAllEvent, GroupMessageReactionRemoveEvent}
import net.dv8tion.jda.client.events.relationship._
import net.dv8tion.jda.core.events._
import net.dv8tion.jda.core.events.channel.category.{CategoryCreateEvent, CategoryDeleteEvent, GenericCategoryEvent}
import net.dv8tion.jda.core.events.channel.category.update.{CategoryUpdateNameEvent, CategoryUpdatePermissionsEvent, CategoryUpdatePositionEvent, GenericCategoryUpdateEvent}
import net.dv8tion.jda.core.events.channel.priv.{PrivateChannelCreateEvent, PrivateChannelDeleteEvent}
import net.dv8tion.jda.core.events.channel.text.{GenericTextChannelEvent, TextChannelCreateEvent, TextChannelDeleteEvent}
import net.dv8tion.jda.core.events.channel.text.update._
import net.dv8tion.jda.core.events.channel.voice.{GenericVoiceChannelEvent, VoiceChannelCreateEvent, VoiceChannelDeleteEvent}
import net.dv8tion.jda.core.events.channel.voice.update._
import net.dv8tion.jda.core.events.emote.{EmoteAddedEvent, EmoteRemovedEvent, GenericEmoteEvent}
import net.dv8tion.jda.core.events.emote.update.{EmoteUpdateNameEvent, EmoteUpdateRolesEvent, GenericEmoteUpdateEvent}
import net.dv8tion.jda.core.events.guild._
import net.dv8tion.jda.core.events.guild.member._
import net.dv8tion.jda.core.events.guild.update._
import net.dv8tion.jda.core.events.guild.voice._
import net.dv8tion.jda.core.events.http.HttpRequestEvent
import net.dv8tion.jda.core.events.message._
import net.dv8tion.jda.core.events.message.guild._
import net.dv8tion.jda.core.events.message.guild.react.{GenericGuildMessageReactionEvent, GuildMessageReactionAddEvent, GuildMessageReactionRemoveAllEvent, GuildMessageReactionRemoveEvent}
import net.dv8tion.jda.core.events.message.priv._
import net.dv8tion.jda.core.events.message.priv.react.{GenericPrivateMessageReactionEvent, PrivateMessageReactionAddEvent, PrivateMessageReactionRemoveEvent}
import net.dv8tion.jda.core.events.message.react.{GenericMessageReactionEvent, MessageReactionAddEvent, MessageReactionRemoveAllEvent, MessageReactionRemoveEvent}
import net.dv8tion.jda.core.events.role.{GenericRoleEvent, RoleCreateEvent, RoleDeleteEvent}
import net.dv8tion.jda.core.events.role.update._
import net.dv8tion.jda.core.events.self._
import net.dv8tion.jda.core.events.user.{GenericUserEvent, UserTypingEvent}
import net.dv8tion.jda.core.events.user.update._
import net.dv8tion.jda.core.hooks.EventListener

object DiscordListener {
  def onGenericEvent(handler: Event => Unit): EventListener = (event: Event) => handler(event)

  def onGenericUpdate(handler: UpdateEvent[_, _] => Unit): EventListener = {
    case e: UpdateEvent[_, _] => handler(e)
    case _ => ()
  }

  //JDA Events
  def onReady(handler: ReadyEvent => Unit): EventListener = {
    case e: ReadyEvent => handler(e)
    case _ => ()
  }
  def onResume(handler: ResumedEvent => Unit): EventListener = {
    case e: ResumedEvent => handler(e)
    case _ => ()
  }
  def onReconnect(handler: ReconnectedEvent => Unit): EventListener = {
    case e: ReconnectedEvent => handler(e)
    case _ => ()
  }
  def onDisconnect(handler: DisconnectEvent => Unit): EventListener = {
    case e: DisconnectEvent => handler(e)
    case _ => ()
  }
  def onShutdown(handler: ShutdownEvent => Unit): EventListener = {
    case e: ShutdownEvent => handler(e)
    case _ => ()
  }
  def onStatusChange(handler: StatusChangeEvent => Unit): EventListener = {
    case e: StatusChangeEvent => handler(e)
    case _ => ()
  }
  def onException(handler: ExceptionEvent => Unit): EventListener = {
    case e: ExceptionEvent => handler(e)
    case _ => ()
  }

  //User Events
  def onUserUpdateName(handler: UserUpdateNameEvent => Unit): EventListener = {
    case e: UserUpdateNameEvent => handler(e)
    case _ => ()
  }
  def onUserUpdateDiscriminator(handler: UserUpdateDiscriminatorEvent => Unit): EventListener = {
    case e: UserUpdateDiscriminatorEvent => handler(e)
    case _ => ()
  }
  def onUserUpdateAvatar(handler: UserUpdateAvatarEvent => Unit): EventListener = {
    case e: UserUpdateAvatarEvent => handler(e)
    case _ => ()
  }
  def onUserUpdateOnlineStatus(handler: UserUpdateOnlineStatusEvent => Unit): EventListener = {
    case e: UserUpdateOnlineStatusEvent => handler(e)
    case _ => ()
  }
  def onUserUpdateGame(handler: UserUpdateGameEvent => Unit): EventListener = {
    case e: UserUpdateGameEvent => handler(e)
    case _ => ()
  }
  def onUserTyping(handler: UserTypingEvent => Unit): EventListener = {
    case e: UserTypingEvent => handler(e)
    case _ => ()
  }

  //Self Events. Fires only in relation to the currently logged in account.
  def onSelfUpdateAvatar(handler: SelfUpdateAvatarEvent => Unit): EventListener = {
    case e: SelfUpdateAvatarEvent => handler(e)
    case _ => ()
  }
  def onSelfUpdateEmail(handler: SelfUpdateEmailEvent => Unit): EventListener = {
    case e: SelfUpdateEmailEvent => handler(e)
    case _ => ()
  }
  def onSelfUpdateMFA(handler: SelfUpdateMFAEvent => Unit): EventListener = {
    case e: SelfUpdateMFAEvent => handler(e)
    case _ => ()
  }
  def onSelfUpdateName(handler: SelfUpdateNameEvent => Unit): EventListener = {
    case e: SelfUpdateNameEvent => handler(e)
    case _ => ()
  }
  def onSelfUpdateVerified(handler: SelfUpdateVerifiedEvent => Unit): EventListener = {
    case e: SelfUpdateVerifiedEvent => handler(e)
    case _ => ()
  }

  //Message Events
  //Guild (TextChannel) Message Events
  def onGuildMessageReceived(handler: GuildMessageReceivedEvent => Unit): EventListener = {
    case e: GuildMessageReceivedEvent => handler(e)
    case _ => ()
  }
  def onGuildMessageUpdate(handler: GuildMessageUpdateEvent => Unit): EventListener = {
    case e: GuildMessageUpdateEvent => handler(e)
    case _ => ()
  }
  def onGuildMessageDelete(handler: GuildMessageDeleteEvent => Unit): EventListener = {
    case e: GuildMessageDeleteEvent => handler(e)
    case _ => ()
  }
  def onGuildMessageEmbed(handler: GuildMessageEmbedEvent => Unit): EventListener = {
    case e: GuildMessageEmbedEvent => handler(e)
    case _ => ()
  }
  def onGuildMessageReactionAdd(handler: GuildMessageReactionAddEvent => Unit): EventListener = {
    case e: GuildMessageReactionAddEvent => handler(e)
    case _ => ()
  }
  def onGuildMessageReactionRemove(handler: GuildMessageReactionRemoveEvent => Unit): EventListener = {
    case e: GuildMessageReactionRemoveEvent => handler(e)
    case _ => ()
  }
  def onGuildMessageReactionRemoveAll(handler: GuildMessageReactionRemoveAllEvent => Unit): EventListener = {
    case e: GuildMessageReactionRemoveAllEvent => handler(e)
    case _ => ()
  }

  //Private Message Events
  def onPrivateMessageReceived(handler: PrivateMessageReceivedEvent => Unit): EventListener = {
    case e: PrivateMessageReceivedEvent => handler(e)
    case _ => ()
  }
  def onPrivateMessageUpdate(handler: PrivateMessageUpdateEvent => Unit): EventListener = {
    case e: PrivateMessageUpdateEvent => handler(e)
    case _ => ()
  }
  def onPrivateMessageDelete(handler: PrivateMessageDeleteEvent => Unit): EventListener = {
    case e: PrivateMessageDeleteEvent => handler(e)
    case _ => ()
  }
  def onPrivateMessageEmbed(handler: PrivateMessageEmbedEvent => Unit): EventListener = {
    case e: PrivateMessageEmbedEvent => handler(e)
    case _ => ()
  }
  def onPrivateMessageReactionAdd(handler: PrivateMessageReactionAddEvent => Unit): EventListener = {
    case e: PrivateMessageReactionAddEvent => handler(e)
    case _ => ()
  }
  def onPrivateMessageReactionRemove(handler: PrivateMessageReactionRemoveEvent => Unit): EventListener = {
    case e: PrivateMessageReactionRemoveEvent => handler(e)
    case _ => ()
  }

  //Combined Message Events (Combines Guild and Private message into 1 event)
  def onMessageReceived(handler: MessageReceivedEvent => Unit): EventListener = {
    case e: MessageReceivedEvent => handler(e)
    case _ => ()
  }
  def onMessageUpdate(handler: MessageUpdateEvent => Unit): EventListener = {
    case e: MessageUpdateEvent => handler(e)
    case _ => ()
  }
  def onMessageDelete(handler: MessageDeleteEvent => Unit): EventListener = {
    case e: MessageDeleteEvent => handler(e)
    case _ => ()
  }
  def onMessageBulkDelete(handler: MessageBulkDeleteEvent => Unit): EventListener = {
    case e: MessageBulkDeleteEvent => handler(e)
    case _ => ()
  }
  def onMessageEmbed(handler: MessageEmbedEvent => Unit): EventListener = {
    case e: MessageEmbedEvent => handler(e)
    case _ => ()
  }
  def onMessageReactionAdd(handler: MessageReactionAddEvent => Unit): EventListener = {
    case e: MessageReactionAddEvent => handler(e)
    case _ => ()
  }
  def onMessageReactionRemove(handler: MessageReactionRemoveEvent => Unit): EventListener = {
    case e: MessageReactionRemoveEvent => handler(e)
    case _ => ()
  }
  def onMessageReactionRemoveAll(handler: MessageReactionRemoveAllEvent => Unit): EventListener = {
    case e: MessageReactionRemoveAllEvent => handler(e)
    case _ => ()
  }

  //TextChannel Events
  def onTextChannelDelete(handler: TextChannelDeleteEvent => Unit): EventListener = {
    case e: TextChannelDeleteEvent => handler(e)
    case _ => ()
  }
  def onTextChannelUpdateName(handler: TextChannelUpdateNameEvent => Unit): EventListener = {
    case e: TextChannelUpdateNameEvent => handler(e)
    case _ => ()
  }
  def onTextChannelUpdateTopic(handler: TextChannelUpdateTopicEvent => Unit): EventListener = {
    case e: TextChannelUpdateTopicEvent => handler(e)
    case _ => ()
  }
  def onTextChannelUpdatePosition(handler: TextChannelUpdatePositionEvent => Unit): EventListener = {
    case e: TextChannelUpdatePositionEvent => handler(e)
    case _ => ()
  }
  def onTextChannelUpdatePermissions(handler: TextChannelUpdatePermissionsEvent => Unit): EventListener = {
    case e: TextChannelUpdatePermissionsEvent => handler(e)
    case _ => ()
  }
  def onTextChannelUpdateNSFW(handler: TextChannelUpdateNSFWEvent => Unit): EventListener = {
    case e: TextChannelUpdateNSFWEvent => handler(e)
    case _ => ()
  }
  def onTextChannelUpdateParent(handler: TextChannelUpdateParentEvent => Unit): EventListener = {
    case e: TextChannelUpdateParentEvent => handler(e)
    case _ => ()
  }
  def onTextChannelCreate(handler: TextChannelCreateEvent => Unit): EventListener = {
    case e: TextChannelCreateEvent => handler(e)
    case _ => ()
  }

  //VoiceChannel Events
  def onVoiceChannelDelete(handler: VoiceChannelDeleteEvent => Unit): EventListener = {
    case e: VoiceChannelDeleteEvent => handler(e)
    case _ => ()
  }
  def onVoiceChannelUpdateName(handler: VoiceChannelUpdateNameEvent => Unit): EventListener = {
    case e: VoiceChannelUpdateNameEvent => handler(e)
    case _ => ()
  }
  def onVoiceChannelUpdatePosition(handler: VoiceChannelUpdatePositionEvent => Unit): EventListener = {
    case e: VoiceChannelUpdatePositionEvent => handler(e)
    case _ => ()
  }
  def onVoiceChannelUpdateUserLimit(handler: VoiceChannelUpdateUserLimitEvent => Unit): EventListener = {
    case e: VoiceChannelUpdateUserLimitEvent => handler(e)
    case _ => ()
  }
  def onVoiceChannelUpdateBitrate(handler: VoiceChannelUpdateBitrateEvent => Unit): EventListener = {
    case e: VoiceChannelUpdateBitrateEvent => handler(e)
    case _ => ()
  }
  def onVoiceChannelUpdatePermissions(handler: VoiceChannelUpdatePermissionsEvent => Unit): EventListener = {
    case e: VoiceChannelUpdatePermissionsEvent => handler(e)
    case _ => ()
  }
  def onVoiceChannelUpdateParent(handler: VoiceChannelUpdateParentEvent => Unit): EventListener = {
    case e: VoiceChannelUpdateParentEvent => handler(e)
    case _ => ()
  }
  def onVoiceChannelCreate(handler: VoiceChannelCreateEvent => Unit): EventListener = {
    case e: VoiceChannelCreateEvent => handler(e)
    case _ => ()
  }

  //Category Events
  def onCategoryDelete(handler: CategoryDeleteEvent => Unit): EventListener = {
    case e: CategoryDeleteEvent => handler(e)
    case _ => ()
  }
  def onCategoryUpdateName(handler: CategoryUpdateNameEvent => Unit): EventListener = {
    case e: CategoryUpdateNameEvent => handler(e)
    case _ => ()
  }
  def onCategoryUpdatePosition(handler: CategoryUpdatePositionEvent => Unit): EventListener = {
    case e: CategoryUpdatePositionEvent => handler(e)
    case _ => ()
  }
  def onCategoryUpdatePermissions(handler: CategoryUpdatePermissionsEvent => Unit): EventListener = {
    case e: CategoryUpdatePermissionsEvent => handler(e)
    case _ => ()
  }
  def onCategoryCreate(handler: CategoryCreateEvent => Unit): EventListener = {
    case e: CategoryCreateEvent => handler(e)
    case _ => ()
  }

  //PrivateChannel Events
  def onPrivateChannelCreate(handler: PrivateChannelCreateEvent => Unit): EventListener = {
    case e: PrivateChannelCreateEvent => handler(e)
    case _ => ()
  }
  def onPrivateChannelDelete(handler: PrivateChannelDeleteEvent => Unit): EventListener = {
    case e: PrivateChannelDeleteEvent => handler(e)
    case _ => ()
  }

  //Guild Events
  def onGuildJoin(handler: GuildJoinEvent => Unit): EventListener = {
    case e: GuildJoinEvent => handler(e)
    case _ => ()
  }
  def onGuildLeave(handler: GuildLeaveEvent => Unit): EventListener = {
    case e: GuildLeaveEvent => handler(e)
    case _ => ()
  }
  def onGuildAvailable(handler: GuildAvailableEvent => Unit): EventListener = {
    case e: GuildAvailableEvent => handler(e)
    case _ => ()
  }
  def onGuildUnavailable(handler: GuildUnavailableEvent => Unit): EventListener = {
    case e: GuildUnavailableEvent => handler(e)
    case _ => ()
  }
  def onUnavailableGuildJoined(handler: UnavailableGuildJoinedEvent => Unit): EventListener = {
    case e: UnavailableGuildJoinedEvent => handler(e)
    case _ => ()
  }
  def onGuildBan(handler: GuildBanEvent => Unit): EventListener = {
    case e: GuildBanEvent => handler(e)
    case _ => ()
  }
  def onGuildUnban(handler: GuildUnbanEvent => Unit): EventListener = {
    case e: GuildUnbanEvent => handler(e)
    case _ => ()
  }

  //Guild Update Events
  def onGuildUpdateAfkChannel(handler: GuildUpdateAfkChannelEvent => Unit): EventListener = {
    case e: GuildUpdateAfkChannelEvent => handler(e)
    case _ => ()
  }
  def onGuildUpdateSystemChannel(handler: GuildUpdateSystemChannelEvent => Unit): EventListener = {
    case e: GuildUpdateSystemChannelEvent => handler(e)
    case _ => ()
  }
  def onGuildUpdateAfkTimeout(handler: GuildUpdateAfkTimeoutEvent => Unit): EventListener = {
    case e: GuildUpdateAfkTimeoutEvent => handler(e)
    case _ => ()
  }
  def onGuildUpdateExplicitContentLevel(handler: GuildUpdateExplicitContentLevelEvent => Unit): EventListener = {
    case e: GuildUpdateExplicitContentLevelEvent => handler(e)
    case _ => ()
  }
  def onGuildUpdateIcon(handler: GuildUpdateIconEvent => Unit): EventListener = {
    case e: GuildUpdateIconEvent => handler(e)
    case _ => ()
  }
  def onGuildUpdateMFALevel(handler: GuildUpdateMFALevelEvent => Unit): EventListener = {
    case e: GuildUpdateMFALevelEvent => handler(e)
    case _ => ()
  }
  def onGuildUpdateName(handler: GuildUpdateNameEvent => Unit): EventListener = {
    case e: GuildUpdateNameEvent => handler(e)
    case _ => ()
  }
  def onGuildUpdateNotificationLevel(handler: GuildUpdateNotificationLevelEvent => Unit): EventListener = {
    case e: GuildUpdateNotificationLevelEvent => handler(e)
    case _ => ()
  }
  def onGuildUpdateOwner(handler: GuildUpdateOwnerEvent => Unit): EventListener = {
    case e: GuildUpdateOwnerEvent => handler(e)
    case _ => ()
  }
  def onGuildUpdateRegion(handler: GuildUpdateRegionEvent => Unit): EventListener = {
    case e: GuildUpdateRegionEvent => handler(e)
    case _ => ()
  }
  def onGuildUpdateSplash(handler: GuildUpdateSplashEvent => Unit): EventListener = {
    case e: GuildUpdateSplashEvent => handler(e)
    case _ => ()
  }
  def onGuildUpdateVerificationLevel(handler: GuildUpdateVerificationLevelEvent => Unit): EventListener = {
    case e: GuildUpdateVerificationLevelEvent => handler(e)
    case _ => ()
  }
  def onGuildUpdateFeatures(handler: GuildUpdateFeaturesEvent => Unit): EventListener = {
    case e: GuildUpdateFeaturesEvent => handler(e)
    case _ => ()
  }

  //Guild Member Events
  def onGuildMemberJoin(handler: GuildMemberJoinEvent => Unit): EventListener = {
    case e: GuildMemberJoinEvent => handler(e)
    case _ => ()
  }
  def onGuildMemberLeave(handler: GuildMemberLeaveEvent => Unit): EventListener = {
    case e: GuildMemberLeaveEvent => handler(e)
    case _ => ()
  }
  def onGuildMemberRoleAdd(handler: GuildMemberRoleAddEvent => Unit): EventListener = {
    case e: GuildMemberRoleAddEvent => handler(e)
    case _ => ()
  }
  def onGuildMemberRoleRemove(handler: GuildMemberRoleRemoveEvent => Unit): EventListener = {
    case e: GuildMemberRoleRemoveEvent => handler(e)
    case _ => ()
  }
  def onGuildMemberNickChange(handler: GuildMemberNickChangeEvent => Unit): EventListener = {
    case e: GuildMemberNickChangeEvent => handler(e)
    case _ => ()
  }

  //Guild Voice Events
  def onGuildVoiceUpdate(handler: GuildVoiceUpdateEvent => Unit): EventListener = {
    case e: GuildVoiceUpdateEvent => handler(e)
    case _ => ()
  }
  def onGuildVoiceJoin(handler: GuildVoiceJoinEvent => Unit): EventListener = {
    case e: GuildVoiceJoinEvent => handler(e)
    case _ => ()
  }
  def onGuildVoiceMove(handler: GuildVoiceMoveEvent => Unit): EventListener = {
    case e: GuildVoiceMoveEvent => handler(e)
    case _ => ()
  }
  def onGuildVoiceLeave(handler: GuildVoiceLeaveEvent => Unit): EventListener = {
    case e: GuildVoiceLeaveEvent => handler(e)
    case _ => ()
  }
  def onGuildVoiceMute(handler: GuildVoiceMuteEvent => Unit): EventListener = {
    case e: GuildVoiceMuteEvent => handler(e)
    case _ => ()
  }
  def onGuildVoiceDeafen(handler: GuildVoiceDeafenEvent => Unit): EventListener = {
    case e: GuildVoiceDeafenEvent => handler(e)
    case _ => ()
  }
  def onGuildVoiceGuildMute(handler: GuildVoiceGuildMuteEvent => Unit): EventListener = {
    case e: GuildVoiceGuildMuteEvent => handler(e)
    case _ => ()
  }
  def onGuildVoiceGuildDeafen(handler: GuildVoiceGuildDeafenEvent => Unit): EventListener = {
    case e: GuildVoiceGuildDeafenEvent => handler(e)
    case _ => ()
  }
  def onGuildVoiceSelfMute(handler: GuildVoiceSelfMuteEvent => Unit): EventListener = {
    case e: GuildVoiceSelfMuteEvent => handler(e)
    case _ => ()
  }
  def onGuildVoiceSelfDeafen(handler: GuildVoiceSelfDeafenEvent => Unit): EventListener = {
    case e: GuildVoiceSelfDeafenEvent => handler(e)
    case _ => ()
  }
  def onGuildVoiceSuppress(handler: GuildVoiceSuppressEvent => Unit): EventListener = {
    case e: GuildVoiceSuppressEvent => handler(e)
    case _ => ()
  }

  //Role events
  def onRoleCreate(handler: RoleCreateEvent => Unit): EventListener = {
    case e: RoleCreateEvent => handler(e)
    case _ => ()
  }
  def onRoleDelete(handler: RoleDeleteEvent => Unit): EventListener = {
    case e: RoleDeleteEvent => handler(e)
    case _ => ()
  }

  //Role Update Events
  def onRoleUpdateColor(handler: RoleUpdateColorEvent => Unit): EventListener = {
    case e: RoleUpdateColorEvent => handler(e)
    case _ => ()
  }
  def onRoleUpdateHoisted(handler: RoleUpdateHoistedEvent => Unit): EventListener = {
    case e: RoleUpdateHoistedEvent => handler(e)
    case _ => ()
  }
  def onRoleUpdateMentionable(handler: RoleUpdateMentionableEvent => Unit): EventListener = {
    case e: RoleUpdateMentionableEvent => handler(e)
    case _ => ()
  }
  def onRoleUpdateName(handler: RoleUpdateNameEvent => Unit): EventListener = {
    case e: RoleUpdateNameEvent => handler(e)
    case _ => ()
  }
  def onRoleUpdatePermissions(handler: RoleUpdatePermissionsEvent => Unit): EventListener = {
    case e: RoleUpdatePermissionsEvent => handler(e)
    case _ => ()
  }
  def onRoleUpdatePosition(handler: RoleUpdatePositionEvent => Unit): EventListener = {
    case e: RoleUpdatePositionEvent => handler(e)
    case _ => ()
  }

  //Emote Events
  def onEmoteAdded(handler: EmoteAddedEvent => Unit): EventListener = {
    case e: EmoteAddedEvent => handler(e)
    case _ => ()
  }
  def onEmoteRemoved(handler: EmoteRemovedEvent => Unit): EventListener = {
    case e: EmoteRemovedEvent => handler(e)
    case _ => ()
  }

  //Emote Update Events
  def onEmoteUpdateName(handler: EmoteUpdateNameEvent => Unit): EventListener = {
    case e: EmoteUpdateNameEvent => handler(e)
    case _ => ()
  }
  def onEmoteUpdateRoles(handler: EmoteUpdateRolesEvent => Unit): EventListener = {
    case e: EmoteUpdateRolesEvent => handler(e)
    case _ => ()
  }

  // Debug Events
  def onHttpRequest(handler: HttpRequestEvent => Unit): EventListener = {
    case e: HttpRequestEvent => handler(e)
    case _ => ()
  }

  //Generic Events
  def onGenericMessage(handler: GenericMessageEvent => Unit): EventListener = {
    case e: GenericMessageEvent => handler(e)
    case _ => ()
  }
  def onGenericMessageReaction(handler: GenericMessageReactionEvent => Unit): EventListener = {
    case e: GenericMessageReactionEvent => handler(e)
    case _ => ()
  }
  def onGenericGuildMessage(handler: GenericGuildMessageEvent => Unit): EventListener = {
    case e: GenericGuildMessageEvent => handler(e)
    case _ => ()
  }
  def onGenericGuildMessageReaction(handler: GenericGuildMessageReactionEvent => Unit): EventListener = {
    case e: GenericGuildMessageReactionEvent => handler(e)
    case _ => ()
  }
  def onGenericPrivateMessage(handler: GenericPrivateMessageEvent => Unit): EventListener = {
    case e: GenericPrivateMessageEvent => handler(e)
    case _ => ()
  }
  def onGenericPrivateMessageReaction(handler: GenericPrivateMessageReactionEvent => Unit): EventListener = {
    case e: GenericPrivateMessageReactionEvent => handler(e)
    case _ => ()
  }
  def onGenericUser(handler: GenericUserEvent => Unit): EventListener = {
    case e: GenericUserEvent => handler(e)
    case _ => ()
  }
  def onGenericUserPresence(handler: GenericUserPresenceEvent[_] => Unit): EventListener = {
    case e: GenericUserPresenceEvent[_] => handler(e)
    case _ => ()
  }
  def onGenericSelfUpdate(handler: GenericSelfUpdateEvent[_] => Unit): EventListener = {
    case e: GenericSelfUpdateEvent[_] => handler(e)
    case _ => ()
  }
  def onGenericTextChannel(handler: GenericTextChannelEvent => Unit): EventListener = {
    case e: GenericTextChannelEvent => handler(e)
    case _ => ()
  }
  def onGenericTextChannelUpdate(handler: GenericTextChannelUpdateEvent[_] => Unit): EventListener = {
    case e: GenericTextChannelUpdateEvent[_] => handler(e)
    case _ => ()
  }
  def onGenericVoiceChannel(handler: GenericVoiceChannelEvent => Unit): EventListener = {
    case e: GenericVoiceChannelEvent => handler(e)
    case _ => ()
  }
  def onGenericVoiceChannelUpdate(handler: GenericVoiceChannelUpdateEvent[_] => Unit): EventListener = {
    case e: GenericVoiceChannelUpdateEvent[_] => handler(e)
    case _ => ()
  }
  def onGenericCategory(handler: GenericCategoryEvent => Unit): EventListener = {
    case e: GenericCategoryEvent => handler(e)
    case _ => ()
  }
  def onGenericCategoryUpdate(handler: GenericCategoryUpdateEvent[_] => Unit): EventListener = {
    case e: GenericCategoryUpdateEvent[_] => handler(e)
    case _ => ()
  }
  def onGenericGuild(handler: GenericGuildEvent => Unit): EventListener = {
    case e: GenericGuildEvent => handler(e)
    case _ => ()
  }
  def onGenericGuildUpdate(handler: GenericGuildUpdateEvent[_] => Unit): EventListener = {
    case e: GenericGuildUpdateEvent[_] => handler(e)
    case _ => ()
  }
  def onGenericGuildMember(handler: GenericGuildMemberEvent => Unit): EventListener = {
    case e: GenericGuildMemberEvent => handler(e)
    case _ => ()
  }
  def onGenericGuildVoice(handler: GenericGuildVoiceEvent => Unit): EventListener = {
    case e: GenericGuildVoiceEvent => handler(e)
    case _ => ()
  }
  def onGenericRole(handler: GenericRoleEvent => Unit): EventListener = {
    case e: GenericRoleEvent => handler(e)
    case _ => ()
  }
  def onGenericRoleUpdate(handler: GenericRoleUpdateEvent[_] => Unit): EventListener = {
    case e: GenericRoleUpdateEvent[_] => handler(e)
    case _ => ()
  }
  def onGenericEmote(handler: GenericEmoteEvent => Unit): EventListener = {
    case e: GenericEmoteEvent => handler(e)
    case _ => ()
  }
  def onGenericEmoteUpdate(handler: GenericEmoteUpdateEvent[_] => Unit): EventListener = {
    case e: GenericEmoteUpdateEvent[_] => handler(e)
    case _ => ()
  }


  // ==========================================================================================
  // |                                   Client Only Events                                   |
  // ==========================================================================================

  //Relationship Events
  def onFriendAdded(handler: FriendAddedEvent => Unit): EventListener = {
    case e: FriendAddedEvent => handler(e)
    case _ => ()
  }
  def onFriendRemoved(handler: FriendRemovedEvent => Unit): EventListener = {
    case e: FriendRemovedEvent => handler(e)
    case _ => ()
  }
  def onUserBlocked(handler: UserBlockedEvent => Unit): EventListener = {
    case e: UserBlockedEvent => handler(e)
    case _ => ()
  }
  def onUserUnblocked(handler: UserUnblockedEvent => Unit): EventListener = {
    case e: UserUnblockedEvent => handler(e)
    case _ => ()
  }
  def onFriendRequestSent(handler: FriendRequestSentEvent => Unit): EventListener = {
    case e: FriendRequestSentEvent => handler(e)
    case _ => ()
  }
  def onFriendRequestCanceled(handler: FriendRequestCanceledEvent => Unit): EventListener = {
    case e: FriendRequestCanceledEvent => handler(e)
    case _ => ()
  }
  def onFriendRequestReceived(handler: FriendRequestReceivedEvent => Unit): EventListener = {
    case e: FriendRequestReceivedEvent => handler(e)
    case _ => ()
  }
  def onFriendRequestIgnored(handler: FriendRequestIgnoredEvent => Unit): EventListener = {
    case e: FriendRequestIgnoredEvent => handler(e)
    case _ => ()
  }

  //Group Events
  def onGroupJoin(handler: GroupJoinEvent => Unit): EventListener = {
    case e: GroupJoinEvent => handler(e)
    case _ => ()
  }
  def onGroupLeave(handler: GroupLeaveEvent => Unit): EventListener = {
    case e: GroupLeaveEvent => handler(e)
    case _ => ()
  }
  def onGroupUserJoin(handler: GroupUserJoinEvent => Unit): EventListener = {
    case e: GroupUserJoinEvent => handler(e)
    case _ => ()
  }
  def onGroupUserLeave(handler: GroupUserLeaveEvent => Unit): EventListener = {
    case e: GroupUserLeaveEvent => handler(e)
    case _ => ()
  }

  //Group Message Events
  def onGroupMessageReceived(handler: GroupMessageReceivedEvent => Unit): EventListener = {
    case e: GroupMessageReceivedEvent => handler(e)
    case _ => ()
  }
  def onGroupMessageUpdate(handler: GroupMessageUpdateEvent => Unit): EventListener = {
    case e: GroupMessageUpdateEvent => handler(e)
    case _ => ()
  }
  def onGroupMessageDelete(handler: GroupMessageDeleteEvent => Unit): EventListener = {
    case e: GroupMessageDeleteEvent => handler(e)
    case _ => ()
  }
  def onGroupMessageEmbed(handler: GroupMessageEmbedEvent => Unit): EventListener = {
    case e: GroupMessageEmbedEvent => handler(e)
    case _ => ()
  }
  def onGroupMessageReactionAdd(handler: GroupMessageReactionAddEvent => Unit): EventListener = {
    case e: GroupMessageReactionAddEvent => handler(e)
    case _ => ()
  }
  def onGroupMessageReactionRemove(handler: GroupMessageReactionRemoveEvent => Unit): EventListener = {
    case e: GroupMessageReactionRemoveEvent => handler(e)
    case _ => ()
  }
  def onGroupMessageReactionRemoveAll(handler: GroupMessageReactionRemoveAllEvent => Unit): EventListener = {
    case e: GroupMessageReactionRemoveAllEvent => handler(e)
    case _ => ()
  }

  //Group Update Events
  def onGroupUpdateIcon(handler: GroupUpdateIconEvent => Unit): EventListener = {
    case e: GroupUpdateIconEvent => handler(e)
    case _ => ()
  }
  def onGroupUpdateName(handler: GroupUpdateNameEvent => Unit): EventListener = {
    case e: GroupUpdateNameEvent => handler(e)
    case _ => ()
  }
  def onGroupUpdateOwner(handler: GroupUpdateOwnerEvent => Unit): EventListener = {
    case e: GroupUpdateOwnerEvent => handler(e)
    case _ => ()
  }

  //Call Events
  def onCallCreate(handler: CallCreateEvent => Unit): EventListener = {
    case e: CallCreateEvent => handler(e)
    case _ => ()
  }
  def onCallDelete(handler: CallDeleteEvent => Unit): EventListener = {
    case e: CallDeleteEvent => handler(e)
    case _ => ()
  }

  //Call Update Events
  def onCallUpdateRegion(handler: CallUpdateRegionEvent => Unit): EventListener = {
    case e: CallUpdateRegionEvent => handler(e)
    case _ => ()
  }
  def onCallUpdateRingingUsers(handler: CallUpdateRingingUsersEvent => Unit): EventListener = {
    case e: CallUpdateRingingUsersEvent => handler(e)
    case _ => ()
  }

  //Call Voice Events
  def onCallVoiceJoin(handler: CallVoiceJoinEvent => Unit): EventListener = {
    case e: CallVoiceJoinEvent => handler(e)
    case _ => ()
  }
  def onCallVoiceLeave(handler: CallVoiceLeaveEvent => Unit): EventListener = {
    case e: CallVoiceLeaveEvent => handler(e)
    case _ => ()
  }
  def onCallVoiceSelfMute(handler: CallVoiceSelfMuteEvent => Unit): EventListener = {
    case e: CallVoiceSelfMuteEvent => handler(e)
    case _ => ()
  }
  def onCallVoiceSelfDeafen(handler: CallVoiceSelfDeafenEvent => Unit): EventListener = {
    case e: CallVoiceSelfDeafenEvent => handler(e)
    case _ => ()
  }

  //Client Only Generic Events
  def onGenericRelationship(handler: GenericRelationshipEvent => Unit): EventListener = {
    case e: GenericRelationshipEvent => handler(e)
    case _ => ()
  }
  def onGenericRelationshipAdd(handler: GenericRelationshipAddEvent => Unit): EventListener = {
    case e: GenericRelationshipAddEvent => handler(e)
    case _ => ()
  }
  def onGenericRelationshipRemove(handler: GenericRelationshipRemoveEvent => Unit): EventListener = {
    case e: GenericRelationshipRemoveEvent => handler(e)
    case _ => ()
  }
  def onGenericGroup(handler: GenericGroupEvent => Unit): EventListener = {
    case e: GenericGroupEvent => handler(e)
    case _ => ()
  }
  def onGenericGroupMessage(handler: GenericGroupMessageEvent => Unit): EventListener = {
    case e: GenericGroupMessageEvent => handler(e)
    case _ => ()
  }
  def onGenericGroupMessageReaction(handler: GenericGroupMessageReactionEvent => Unit): EventListener = {
    case e: GenericGroupMessageReactionEvent => handler(e)
    case _ => ()
  }
  def onGenericGroupUpdate(handler: GenericGroupUpdateEvent => Unit): EventListener = {
    case e: GenericGroupUpdateEvent => handler(e)
    case _ => ()
  }
  def onGenericCall(handler: GenericCallEvent => Unit): EventListener = {
    case e: GenericCallEvent => handler(e)
    case _ => ()
  }
  def onGenericCallUpdate(handler: GenericCallUpdateEvent => Unit): EventListener = {
    case e: GenericCallUpdateEvent => handler(e)
    case _ => ()
  }
  def onGenericCallVoice(handler: GenericCallVoiceEvent => Unit): EventListener = {
    case e: GenericCallVoiceEvent => handler(e)
    case _ => ()
  }
}
