package com.home.notification

import com.home.rabbitmq.DomainSubscriber
import com.home.rabbitmq.DomainSubscriberRegistry
import com.home.rabbitmq.RabbitConsumer
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

private val notifications = mutableListOf<String>()

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        get("/notifications") {
            call.respondText(notifications.toString())
        }
    }
    DomainSubscriberRegistry.register(UserCreatedEventSubscriber())
    RabbitConsumer().registerSubscribers()
}

class UserCreatedEventSubscriber : DomainSubscriber<UserCreatedEvent>() {
    override fun on(event: Any) {
        notifications.add((event as UserCreatedEvent).name)
    }

    override fun queue() = "user.created"
    override fun genericClass() = UserCreatedEvent::class.java
}

