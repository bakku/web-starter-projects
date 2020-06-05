package playground.persistence

import java.sql.Connection

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}

import scala.util.Try

object DataSource {
  def withConnection[T](orElse: => T)(f: Connection => T): T = {
    Try(ds.getConnection).map { conn =>
      val ret = f(conn)
      conn.close()
      ret
    }.getOrElse(orElse)
  }

  private lazy val ds = {
    val config = new HikariConfig("/hikari.properties")
    new HikariDataSource(config)
  }
}
