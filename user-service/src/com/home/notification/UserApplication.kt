package com.home.notification

import com.home.rabbitmq.DomainEventPublisher
import com.home.rabbitmq.RabbitProducer
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

private val producer: DomainEventPublisher = RabbitProducer()

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        post("/users") {
            producer.publish(call.receiveText(), "sample-queue")
            call.response.status(HttpStatusCode.Accepted)
        }
    }
}

