package com.home.user

import com.home.kafka.KafkaPublisher
import com.home.user.create.UserCreationContext
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val publisher = KafkaPublisher()
    val context = UserCreationContext(publisher)
    context.configureContext()

    routing {
        post("/users") {
            context.userCreator.create(call.receiveText())
            call.response.status(HttpStatusCode.Accepted)
        }
    }
}

