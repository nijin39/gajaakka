package com.tandem6.attraction

import akka.actor.{Actor, ActorLogging, Props}

object JobWorker {
  def props = Props(new JobWorker)
}

class JobWorker extends Actor
  with ActorLogging {
  override def receive: Receive = ???
}