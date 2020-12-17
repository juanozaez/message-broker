package com.home.user

import com.home.rabbitmq.RabbitProducer
import com.home.user.create.domain.UserCreator
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

private val publisher = RabbitProducer()
private val creator = UserCreator(publisher)

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        post("/users") {
            creator.create(call.receiveText())
            call.response.status(HttpStatusCode.Accepted)
        }
    }
}

