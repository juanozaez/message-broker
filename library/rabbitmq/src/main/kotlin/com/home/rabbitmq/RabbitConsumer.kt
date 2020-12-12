package com.home.rabbitmq

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

class RabbitConsumer {
    private val connection = ConnectionFactory().newConnection("amqp://guest:guest@localhost:5672/")
    private val channel = connection.createChannel()
    private val mappper = jacksonObjectMapper()

    fun waitForMessages(classs: Class<out DomainEvent>, queueName: String, callback: (DomainEvent) -> Unit) {
        val deliverCallback = DeliverCallback {

                _, message ->
            callback(mappper.readValue(message.body.decodeToString(), classs))
        }
        val cancelCallback = CancelCallback { consumerTag ->
            println("Cancel $consumerTag")
        }
        channel.basicConsume(queueName, true, deliverCallback, cancelCallback)
    }

    fun registerSubscribers() {
        DomainSubscriberRegistry.subscribers.forEach { subscriber ->
            waitForMessages(subscriber.genericClass(), subscriber.queue()) {
                subscriber.on(it)
            }
        }
    }
}