package playground.handlers

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model.StatusCodes
import akka.pattern.ask
import akka.util.Timeout
import playground.actors.{GetProduct, ProductRetrieved}
import playground.models.Product.jsonConversion

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class GetProductHandler(system: ActorSystem) extends SprayJsonSupport {
  def handle(id: Long): ToResponseMarshallable = {
    implicit val timeout: Timeout = 60.seconds
    implicit val ec: ExecutionContext = system.dispatcher

    val productRetrievalActor = system.actorSelection("/user/product-retrieval")

    productRetrievalActor.ask(GetProduct(id)).map(returnProduct)
  }

  private def returnProduct(prod: Any): ToResponseMarshallable = prod match {
    case ProductRetrieved(Some(product)) => product
    case ProductRetrieved(None) => StatusCodes.NotFound
  }
}
