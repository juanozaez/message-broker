package com.home.user

import com.home.messagebroker.DomainEventPublisher
import com.home.rabbitmq.RabbitProducer
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.routing.*
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

private val producer: DomainEventPublisher = RabbitProducer()

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        post("/users") {
            producer.publish(UserCreatedEvent(UUID.randomUUID().toString(), call.receiveText()))
            call.response.status(HttpStatusCode.Accepted)
        }
    }
}

