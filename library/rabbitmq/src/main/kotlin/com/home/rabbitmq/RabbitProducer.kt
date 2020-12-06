package com.home.rabbitmq

import com.rabbitmq.client.ConnectionFactory

class RabbitProducer: DomainEventPublisher {
    private val connection = ConnectionFactory().newConnection("amqp://guest:guest@localhost:5672/")
    private val channel = connection.createChannel()

    fun createQueue(queueName: String){
        channel.queueDeclare(queueName, false, false, false, null)
    }

    fun publishMessage(message: String, queueName: String){
        channel.basicPublish("", queueName,null, message.toByteArray() )
    }

    override fun publish(any: Any, queueName: String) {
        publishMessage(any.toString(), queueName)
    }
}