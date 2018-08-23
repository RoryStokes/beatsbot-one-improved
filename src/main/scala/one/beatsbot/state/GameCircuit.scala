package one.beatsbot.state

import diode.{ActionType, Circuit}
import one.beatsbot.model.BotState
import one.beatsbot.state.handlers._

class GameCircuit extends Circuit[BotState]
  with DiscordHandler with BeatHandler with CommandHandler {
  override val initialModel: BotState = BotState.initialState

  override val actionHandler: HandlerFunction = foldHandlers(
    discordHandler, commandHandler, beatHandler
  )

  override def dispatch[A](action: A)(implicit evidence$6: ActionType[A]): Unit = {
    println(action)
    super.dispatch(action)
  }
}