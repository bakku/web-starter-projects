package playground.actors

import akka.actor.Actor
import playground.persistence.ProductRepository

class DatabaseProductActor extends Actor {
  override def receive: Receive = {
    case GetProduct(id) => sender() ! ProductRetrieved(ProductRepository.find(id))
  }
}
