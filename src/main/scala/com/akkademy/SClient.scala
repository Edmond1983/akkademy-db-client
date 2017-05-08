package com.akkademy

import akka.util.Timeout
import akka.actor.ActorSystem
import scala.concurrent.duration._
import akka.pattern.ask
import com.akkademy.messages._

class SClient(remoteAddress: String){
	private implicit val timeout = Timeout(5 seconds)
	private implicit val system = ActorSystem("LocalSystem")
	private val remoteDB = system.actorSelection(s"akka.tcp://akkademy@$remoteAddress/user/akkademy-db")
	def set(key: String, value: Object) = {
		remoteDB ? SetRequest (key, value)
	}
	def get(key: String) = {
		remoteDB ? GetRequest (key)
	}
}
