package com.home.notification

import com.home.kafka.useKafka
import com.home.notification.user.UserContext
import com.home.rabbitmq.useRabbit
import io.ktor.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    // Contexts configuration
    UserContext().configureContext()

    // Rabbit MQ
    useRabbit()

    // Kafka
    //useKafka()
}