package com.home.producer

import org.junit.jupiter.api.Test


internal class RabbitProducerTest{
    private val producer = RabbitProducer()
    private val consumer = RabbitConsumer()

    @Test
    fun `it produces a message and queues to the queue`(){
        val queueName = "sample-queue"
        producer.createQueue(queueName)
        producer.publishMessage("Hello macu", queueName)
        consumer.waitForMessages(queueName)
    }
}