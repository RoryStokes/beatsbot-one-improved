package one.beatsbot.store

import doobie._
import doobie.implicits._
import cats.effect.IO
import one.beatsbot.model.Beat

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Database {
  val dbUrl = sys.env.getOrElse("DB_URL", "jdbc:postgresql:bb1")
  val dbUser = sys.env.getOrElse("DB_USER", "bb1")
  val dbPass = sys.env.getOrElse("DB_PASS", "pass")

  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", dbUrl, dbUser, dbPass
  )

  private def wrapFuture[A](io: IO[A]) = Future {
    io.attempt.unsafeRunSync() match {
      case Right(result) => result
      case Left(e) =>
        e.printStackTrace()
        throw e
    }
  }

  private def getBeatByUrl(url: String) = wrapFuture {
    sql"select id, name, url, points from beats where url=$url".query[Beat].option.transact(xa)
  }


  private def createBeat(name: String, url: String) = wrapFuture {
    sql"insert into beats (name, url, points) values ($name, $url, 0)".update
      .withUniqueGeneratedKeys[Beat]("id", "name", "url", "points")
      .transact(xa)
  }

  def getOrCreateBeat(name: String, url: String): Future[Beat] = {
    getBeatByUrl(url) flatMap {
      case Some(points) => Future.successful(points)
      case None => createBeat(name, url)
    }
  }

  def updatePoints(id: Int, points: Int): Future[Int] = wrapFuture {
    println(s"Updating points for $id to $points")
    sql"update beats set points=$points where id=$id"
      .update.run
      .transact(xa)
  }

  def getTop(number: Int): Future[Seq[Beat]] = wrapFuture {
    sql"select id, name, url, points from beats order by points desc limit $number"
      .query[Beat].to[Seq].transact(xa)
  }

}
