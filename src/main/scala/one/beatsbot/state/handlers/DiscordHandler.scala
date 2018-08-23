package one.beatsbot.state.handlers

import diode.{ActionHandler, ActionResult, Circuit, ModelRW}
import one.beatsbot.discord.DiscordAction
import one.beatsbot.model.BotState
import one.beatsbot.state.actions.DiscordUpdate
import net.dv8tion.jda.core.entities.Guild

trait DiscordHandler { self: Circuit[BotState] =>
  def writeGuild(state: BotState, guild: Option[Guild]): BotState = {
    Option(state).getOrElse(BotState.initialState).copy(guild = guild)
  }
  val guildRW: ModelRW[BotState, Option[Guild]] = zoomRW(_.guild)(writeGuild)

  val discordHandler: ActionHandler[BotState, Option[Guild]] = new ActionHandler(guildRW) {
    override def handle: PartialFunction[Any, ActionResult[BotState]] = {
      case DiscordUpdate(guild) => updated(Option(guild))
      case action: DiscordAction => value.flatMap(action(_)) match {
        case Some(effect) => effectOnly(effect)
        case _ => noChange
      }
    }
  }
}