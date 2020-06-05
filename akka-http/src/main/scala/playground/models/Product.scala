package playground.models

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

case class Product(id: Long, name: String)

object Product {
  implicit val jsonConversion: RootJsonFormat[Product] = jsonFormat2(Product.apply)
}