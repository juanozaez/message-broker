package com.casa

import com.home.producer.RabbitConsumer
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

private val notifications = mutableListOf<String>()

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        get("/notifications"){
            call.respondText (notifications.toString())
        }
    }

    RabbitConsumer().waitForMessages("sample-queue")
}

