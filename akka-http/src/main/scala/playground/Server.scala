package playground

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import playground.actors.{DatabaseProductActor, ProductRetrievalActor}
import playground.persistence.DataSource

import scala.concurrent.ExecutionContext
import scala.io.StdIn

object Server {
  def main(args: Array[String]) {
    implicit val system: ActorSystem = ActorSystem("playground")
    implicit val ec: ExecutionContext = system.dispatcher

    initializeActors(system)

    val router = new Router(system)
    val bindingFuture = Http().bindAndHandle(router.buildRoutes, "localhost", 8080)

    println("Server online at http://localhost:8080/")
    println("Press RETURN to stop...")

    StdIn.readLine()

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }

  def initializeActors(system: ActorSystem): Unit = {
    system.actorOf(Props[DatabaseProductActor].withDispatcher("database-dispatcher"), "database-product")
    system.actorOf(Props[ProductRetrievalActor], "product-retrieval")
  }
}
