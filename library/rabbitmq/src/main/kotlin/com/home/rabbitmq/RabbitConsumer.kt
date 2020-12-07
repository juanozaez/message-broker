package com.home.rabbitmq

import com.beust.klaxon.Klaxon
import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

class RabbitConsumer {
    private val connection = ConnectionFactory().newConnection("amqp://guest:guest@localhost:5672/")
    private val channel = connection.createChannel()
    private val klaxon = Klaxon()

    fun waitForMessages(queueName: String, callback: (DomainEvent) -> Unit){
        val deliverCallback = DeliverCallback {
            _, message -> callback(klaxon.parse(message.body.decodeToString())!!)
        }
        val cancelCallback = CancelCallback {
            consumerTag -> println( "Cancel $consumerTag" )
        }
        channel.basicConsume(queueName, true, deliverCallback, cancelCallback)
    }

    fun registerSubscribers(){
        DomainSubscriberRegistry.subscribers.forEach { subscriber ->
            waitForMessages(subscriber.queue()) {
                subscriber.on(it)
            }
        }
    }
}