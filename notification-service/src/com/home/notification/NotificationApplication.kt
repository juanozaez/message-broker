package com.home.notification

import com.home.rabbitmq.DomainSubscriberRegistry
import com.home.rabbitmq.RabbitConsumer
import io.ktor.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    DomainSubscriberRegistry.register(NotifyUserOnUserCreatedEventSubscriber())
    RabbitConsumer().registerSubscribers()
}