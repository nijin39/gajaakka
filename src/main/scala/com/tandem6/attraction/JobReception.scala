package com.tandem6.attraction

import akka.actor.Actor

object JobReception {
  case class JobRequest(id: String, name: String)
}

class JobReception extends Actor {
  override def receive: Receive = {
    case name:String => {
      sender() ! name
    }
  }
}