package com.home.producer

import com.rabbitmq.client.ConnectionFactory

class RabbitProducer {
    private val connection = ConnectionFactory().newConnection("amqp://guest:guest@localhost:5672/")
    private val channel = connection.createChannel()

    fun createQueue(queueName: String){
        channel.queueDeclare(queueName, false, false, false, null)
    }

    fun publishMessage(message: String, queueName: String){
        channel.basicPublish("", queueName,null, message.toByteArray() )
    }
}