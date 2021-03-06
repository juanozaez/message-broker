package com.home.rabbitmq

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.home.messagebroker.DomainEvent
import com.home.messagebroker.DomainEventPublisher
import com.rabbitmq.client.ConnectionFactory

class RabbitPublisher : DomainEventPublisher {
    private val connection = ConnectionFactory().newConnection("amqp://guest:guest@localhost:5672/")
    private val channel = connection.createChannel()
    private val mappper = jacksonObjectMapper()

    override fun publish(event: DomainEvent) {
        publishMessage(mappper.writeValueAsString(event), event.name())
    }

    private fun publishMessage(message: String, topic: String) {
        channel.basicPublish("domain-events", topic, null, message.toByteArray())
    }
}