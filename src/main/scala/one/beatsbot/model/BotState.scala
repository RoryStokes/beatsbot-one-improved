package one.beatsbot.model

import net.dv8tion.jda.core.entities.{Guild, Message}

final case class BotState(
  nowPlaying: Option[NowPlaying],
  guild: Option[Guild]
)

case class NowPlaying(
  playMessage: Message,
  pointsMessage: Message,
  relevantMessages: Seq[String] = Nil,
  beat: BeatRef,
  storedPoints: Beat,
  queuedBy: Option[UserRef] = None,
  votes: Map[UserRef, Vote] = Map.empty,
)


object BotState {
  val initialState = BotState(None, None)
}
