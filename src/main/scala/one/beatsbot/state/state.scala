package one.beatsbot

import diode.ActionResult

package object state {
  implicit class ApplicativeActionResult[+T](result: ActionResult[T]) {
    def map[B](f: T => B): ActionResult[B] = ActionResult(result.newModelOpt.map(f), result.effectOpt)
  }
}