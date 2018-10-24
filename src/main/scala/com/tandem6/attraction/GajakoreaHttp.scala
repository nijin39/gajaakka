package com.tandem6.attraction

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.{HttpApp, Route}
import akka.util.Timeout
import akka.{Done, pattern}

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import akka.pattern.ask

class GajakoreaHttp(jobReception: ActorRef) extends HttpApp {
  implicit val timeout = Timeout(50000 millis)

  override def routes: Route =
    path("hello" ) {
      get {
        parameters('name.as[String]) { name =>
          val future = jobReception ? name
          val result = Await.result(future, timeout.duration).asInstanceOf[String]
          complete(result)
          }
      }
    }

}