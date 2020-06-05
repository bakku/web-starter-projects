package playground.actors

import akka.actor.Actor
import akka.pattern.{ask, pipe}
import akka.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class ProductRetrievalActor extends Actor {
  implicit val timeout: Timeout = 60.seconds
  implicit val ec: ExecutionContext = context.dispatcher

  override def receive: Receive = {
    case GetProduct(id) =>
      val databaseProductActor = context.actorSelection("/user/database-product")
      databaseProductActor.ask(GetProduct(id)).pipeTo(sender())
  }
}
