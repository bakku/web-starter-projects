name := "playground"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"   % "10.1.11",
  "com.typesafe.akka" %% "akka-stream" % "2.6.1",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.11",
  "com.zaxxer" % "HikariCP" % "3.4.2",
  "com.h2database" % "h2" % "1.4.200",
  "org.slf4j" % "slf4j-simple" % "1.7.30"
)