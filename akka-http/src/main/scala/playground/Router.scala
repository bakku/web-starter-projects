package playground

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import playground.handlers.GetProductHandler

class Router(system: ActorSystem) {
  val getProductHandler = new GetProductHandler(system)

  val buildRoutes: Route =
    concat(
      path("product" / LongNumber) { orderId =>
        get {
          complete(getProductHandler.handle(orderId))
        }
      }
    )
}
