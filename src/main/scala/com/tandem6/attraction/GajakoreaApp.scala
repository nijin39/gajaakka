package com.tandem6.attraction

import akka.actor.{ActorSystem, Props}
import akka.cluster.Cluster
import com.typesafe.config.ConfigFactory
import akka.http.scaladsl.settings.ServerSettings

object GajakoreaApp extends App {

  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")

  implicit val system: ActorSystem = ActorSystem("GAJAKOREA")

  if (system.settings.config.getStringList("akka.cluster.roles").contains("master")) {
    Cluster(system).registerOnMemberUp {
      val receptionist = system.actorOf(Props[JobReceptionist], "receptionist")
      println("Master node is ready.")
      val server = new GajakoreaHttp(receptionist)
      system.actorOf(Props(new ClusterDomainEventListener), "cluster-listener")
      val settings = ServerSettings(ConfigFactory.load).withVerboseErrorMessages(true)
      server.startServer(host, port, settings, system)
    }
  }
}