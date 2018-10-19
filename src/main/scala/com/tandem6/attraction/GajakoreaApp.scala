//#full-example
package com.tandem6.attraction

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.settings.ServerSettings

import scala.io.StdIn

object GajakoreaApp extends App {

  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")

  implicit val system:ActorSystem = ActorSystem("GAJAKOREA")
//  implicit val executionContext = system.dispatcher
//  implicit val materializer = ActorMaterializer()

  val settings = ServerSettings(ConfigFactory.load).withVerboseErrorMessages(true)
  GajakoreaHttp.startServer("localhost",8080, system)
}