package one.beatsbot

import diode.{Circuit, Effect, ModelR}

object Util {
  def subscribeZipped[M <: AnyRef, A, B](circuit: Circuit[M], a: ModelR[M,A], b: ModelR[M,B])(handler: (A, B) => Unit): Unit = {
    def handle(in: Any): Unit = handler(a.value, b.value)
    circuit.subscribe(a)(handle)
    circuit.subscribe(b)(handle)
  }

  def sequenceEffects(effects: Seq[Effect]): Option[Effect] = {
    effects.headOption map { head =>
      effects.tail.foldRight(head)((effect, acc) => acc >> effect)
    }
  }

  def combineEffects(effects: Seq[Effect]): Option[Effect] = {
    effects.headOption map { head =>
      effects.tail.foldRight(head)((effect, acc) => acc + effect)
    }

  }
}
