package com.casa

import com.home.producer.RabbitProducer
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

private val producer = RabbitProducer()

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        post ("/users"){
            println("User created ${call.receiveText()}")
            producer.publishMessage(call.receiveText(), "sample-queue")
        }
    }
}

