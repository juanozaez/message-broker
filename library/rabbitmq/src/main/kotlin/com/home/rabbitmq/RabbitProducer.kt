package com.home.rabbitmq

import com.beust.klaxon.Klaxon
import com.rabbitmq.client.ConnectionFactory

class RabbitProducer: DomainEventPublisher {
    private val connection = ConnectionFactory().newConnection("amqp://guest:guest@localhost:5672/")
    private val channel = connection.createChannel()
    private val klaxon = Klaxon()

    override fun publish(event: DomainEvent) {
        createQueue(event.name())
        publishMessage(klaxon.toJsonString(event), event.name())
    }

    private fun createQueue(queueName: String){
        channel.queueDeclare(queueName, false, false, false, null)
    }

    private fun publishMessage(message: String, queueName: String){
        channel.basicPublish("", queueName,null, message.toByteArray() )
    }
}