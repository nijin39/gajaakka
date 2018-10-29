package com.tandem6.attraction

import akka.actor.{Actor, ActorContext, ActorLogging}

object JobReceptionist {
  def name = "receptionist"
  case class JobRequest(id: String, name: String)
}

class JobReceptionist extends Actor
                        with ActorLogging
                        with CreateMaster {
  override def receive: Receive = {
    case name:String => {
      sender() ! name
    }
  }
}

trait CreateMaster {
  def context: ActorContext
  def createMaster(name: String) = context.actorOf(JobMaster.props, name)
}