package one.beatsbot

package object model {
  type UserId = String
  type ChannelId = String
  type BeatId = Int

  case class MessageId(message: String, channel: ChannelId)

  sealed trait Vote {
    def delta: Int
  }
  case object Updoot extends Vote {
    val delta: Int = 1
  }
  case object Downboat extends Vote {
    val delta: Int = -1
  }

  sealed trait Ref[A] {
    def name: String
    def id: A

    override def toString: String = s"${this.getClass.getSimpleName}($name)"
  }

  final case class UserRef(name: String, id: UserId) extends Ref[UserId]

  final case class BeatRef(name: String, id: BeatId) extends Ref[BeatId]
  final case class Beat(id: Int, name: String, url: String, points: Int)
}