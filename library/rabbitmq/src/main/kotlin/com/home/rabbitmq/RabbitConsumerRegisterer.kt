package com.home.rabbitmq

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.home.messagebroker.DomainEvent
import com.home.messagebroker.DomainSubscriberRegistry
import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import kotlin.reflect.KClass

class RabbitConsumerRegisterer {
    private val connection = ConnectionFactory().newConnection("amqp://guest:guest@localhost:5672/")
    private val channel = connection.createChannel()
    private val mapper = jacksonObjectMapper()

    fun registerSubscribers() {
        DomainSubscriberRegistry.subscribers.forEach { subscriber ->
            waitForMessages(subscriber.subscribedEvent(), subscriber.name(), subscriber.domainEventName()) {
                subscriber.onEvent(it)
            }
        }
    }

    private fun waitForMessages(
        kclass: KClass<out DomainEvent>,
        queueName: String,
        topic: String,
        callback: (DomainEvent) -> Unit
    ) {
        val deliverCallback = DeliverCallback { _, message ->
            callback(mapper.readValue(message.body.decodeToString(), kclass.java))
        }
        val cancelCallback = CancelCallback { consumerTag ->
            println("Cancel $consumerTag")
        }

        channel.exchangeDeclare("domain-events", "topic")
        channel.queueDeclare(queueName, false, false, false, null)
        channel.queueBind(queueName, "domain-events", topic)
        channel.basicConsume(queueName, true, deliverCallback, cancelCallback)
    }
}