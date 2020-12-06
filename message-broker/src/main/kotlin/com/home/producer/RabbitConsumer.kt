package com.home.producer

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

class RabbitConsumer {
    private val connection = ConnectionFactory().newConnection("amqp://guest:guest@localhost:5672/")
    private val channel = connection.createChannel()


    fun waitForMessages(queueName: String){
        val deliverCallback = DeliverCallback {
            consumerTag, message -> println( "Message ${message.body.decodeToString()} received to $consumerTag" )
        }
        val cancelCallback = CancelCallback {
            consumerTag -> println( "Cancel $consumerTag" )
        }
        channel.basicConsume(queueName, true, deliverCallback, cancelCallback)
    }
}