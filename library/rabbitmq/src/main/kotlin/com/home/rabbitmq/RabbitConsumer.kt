package com.home.rabbitmq

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

class RabbitConsumer {
    private val connection = ConnectionFactory().newConnection("amqp://guest:guest@localhost:5672/")
    private val channel = connection.createChannel()


    fun waitForMessages(queueName: String, callback: (String, String) -> Unit){
        val deliverCallback = DeliverCallback {
            consumerTag, message -> callback(consumerTag, message.body.decodeToString())
        }
        val cancelCallback = CancelCallback {
            consumerTag -> println( "Cancel $consumerTag" )
        }
        channel.basicConsume(queueName, true, deliverCallback, cancelCallback)
    }
}