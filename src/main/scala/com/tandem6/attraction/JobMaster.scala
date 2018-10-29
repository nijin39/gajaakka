package com.tandem6.attraction

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.cluster.routing.{ClusterRouterPool, ClusterRouterPoolSettings}
import akka.routing.BroadcastPool

object JobMaster {
  def props = Props(new JobMaster)
}

class JobMaster extends Actor
  with ActorLogging
  with CreateWorkerRouter {
  override def receive: Receive = ???
}

trait CreateWorkerRouter { this: Actor =>
  def createWorkerRouter: ActorRef = {
    context.actorOf(
      ClusterRouterPool(BroadcastPool(10), ClusterRouterPoolSettings(
        totalInstances = 100, maxInstancesPerNode = 20,
        allowLocalRoutees = false, useRole = None)).props(Props[JobWorker]),
      name = "worker-router")
  }
}