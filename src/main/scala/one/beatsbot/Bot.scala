package one.beatsbot

import one.beatsbot.discord.DiscordAction.VoteMessage
import one.beatsbot.discord.{DiscordListener, DiscordService, EmojiService, MarvHandler}
import one.beatsbot.model.{MessageId, Updoot}
import one.beatsbot.state.actions._
import one.beatsbot.state.GameCircuit
import one.beatsbot.state.actions.ReceivedCommand

import scala.collection.JavaConverters._
import scala.language.implicitConversions

object Bot extends App {

  val token = sys.env("DISCORD_TOKEN")

  var circuit = new GameCircuit

  val commandListener = DiscordListener.onMessageReceived { event =>
    event.getMessage.getContentRaw match {
      case CommandMessage(commandName, content) =>
        circuit.dispatch(ReceivedCommand(commandName.toLowerCase,
          from = DiscordService.userRef(event.getMember),
          mentions = event.getMessage.getMentionedMembers.asScala.map(DiscordService.userRef),
          content = Option(content),
          message = event.getMessage
        ))
      case _ => ()
    }
  }

  val circuitListener = DiscordListener.onGenericGuild { event =>
    circuit.dispatch(DiscordUpdate(event.getGuild))
  }

  val messageListener = DiscordListener.onMessageReceived { event =>
    MarvHandler.handleMessage(event.getMessage).foreach(circuit.dispatch(_))
    if(event.getMessage.getContentRaw.toLowerCase.startsWith("what a") ||
      event.getMessage.getContentRaw.toLowerCase.startsWith("updoot")) {
      circuit.dispatch(VoteMessage(DiscordService.userRef(event.getMember), Updoot, event.getMessage))
    }
  }

  val reactionListener = DiscordListener.onMessageReactionAdd { event =>
    if(!event.getUser.isBot) {
      EmojiService.getVote(event.getReactionEmote.getName) foreach { vote =>
        circuit.dispatch(VoteReaction(DiscordService.userRef(event.getMember), vote, event.getMessageId))
      }
    }
  }

  val deleteListener = DiscordListener.onMessageDelete { event =>
    circuit.dispatch(MessageDeleted(event.getMessageId))
  }

  val listeners = Seq(
    DiscordListener.onReady(_ => println("Ready")),
    commandListener,
    circuitListener,
    messageListener,
    reactionListener,
    deleteListener
  )

  val UserRole = "\\$p_(.*)".r
  val CommandMessage = raw"!([a-zA-Z]+)(?: (.*))?".r
  val discord = new DiscordService(token, listeners)
}