package one.beatsbot.state.handlers

import diode.ActionResult.{EffectOnly, NoChange}
import diode.{ActionResult, Circuit, Effect}
import one.beatsbot.discord.DiscordAction.{ListBeats, VoteMessage}
import one.beatsbot.model.{BotState, Downboat, Updoot}
import one.beatsbot.state.actions.ReceivedCommand
import one.beatsbot.store.Database

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Try

trait CommandHandler { self: Circuit[BotState] =>

  def commandHandler(state: BotState, action: Any): Option[ActionResult[BotState]] = (action match {
    case ReceivedCommand(upword, from, _, _, message) if upword.startsWith("up") =>
      Some(EffectOnly(Effect.action(
        VoteMessage(from, Updoot, message)
      )))
    case ReceivedCommand(downword, from, _, _, message) if downword.startsWith("down") =>
      Some(EffectOnly(Effect.action(
        VoteMessage(from, Downboat, message)
      )))
    case ReceivedCommand("top", _, _, content, message) =>
      val number = content.flatMap(text => Try(text.trim.toInt).toOption).getOrElse(10)
      Some(EffectOnly(Effect(
        Database.getTop(number).map { beats =>
          ListBeats(beats, message.getTextChannel)
        }
      )))
    case _ => None
  }) orElse Some(NoChange)

}