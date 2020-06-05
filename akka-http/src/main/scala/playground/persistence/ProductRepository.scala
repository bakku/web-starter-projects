package playground.persistence

import playground.models.Product

object ProductRepository {
  def find(id: Long): Option[Product] = {
    DataSource.withConnection[Option[Product]](None) { conn =>
      val stmt = conn.prepareStatement("SELECT * FROM product WHERE id = ?")
      stmt.setLong(1, id)

      val rs = stmt.executeQuery()

      if (rs.next()) {
        Some(Product(rs.getLong("id"), rs.getString("name")))
      } else {
        None
      }
    }
  }
}
