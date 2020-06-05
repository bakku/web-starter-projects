package playground.actors

import playground.models.Product

case class GetProduct(id: Long)
case class ProductRetrieved(product: Option[Product])