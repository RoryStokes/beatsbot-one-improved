package one.beatsbot.discord

import one.beatsbot.model.{Downboat, Updoot, Vote}

import scala.util.Random

object EmojiService {
  val goodReacts: Seq[String] =Seq("📈","🎺","💯","👌","👍","🔥","🥁")
  val badReacts: Seq[String] = Seq("📉","👎","🚢","🚣","🚤","⚓","💩")

  val updoot: String = "🎺"
  val downboat: String = "⚓"

  val randomReact: Vote => String = {
    case Updoot => goodReacts(Random.nextInt(goodReacts.size))
    case Downboat => badReacts(Random.nextInt(badReacts.size))
  }

  def getVote(react: String): Option[Vote] = react match {
    case r if isGood(r) => Some(Updoot)
    case r if isBad(r) => Some(Downboat)
    case _ => None
  }

  def isGood: String => Boolean = goodReacts.contains
  def isBad: String => Boolean = badReacts.contains
}
