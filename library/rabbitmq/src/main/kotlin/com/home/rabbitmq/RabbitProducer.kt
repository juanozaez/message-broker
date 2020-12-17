package com.home.rabbitmq

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.home.messagebroker.DomainEvent
import com.home.messagebroker.DomainEventPublisher
import com.rabbitmq.client.ConnectionFactory

class RabbitProducer: DomainEventPublisher {
    private val connection = ConnectionFactory().newConnection("amqp://guest:guest@localhost:5672/")
    private val channel = connection.createChannel()
    private val mappper = jacksonObjectMapper()

    override fun publish(event: DomainEvent) {
        createQueue(event.name())
        publishMessage(mappper.writeValueAsString(event), event.name())
    }

    private fun createQueue(queueName: String){
        channel.queueDeclare(queueName, false, false, false, null)
    }

    private fun publishMessage(message: String, queueName: String){
        channel.basicPublish("", queueName,null, message.toByteArray() )
    }
}