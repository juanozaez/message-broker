package com.home.kafka

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.home.messagebroker.DomainEvent
import com.home.messagebroker.DomainSubscriberRegistry
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*
import kotlin.reflect.KClass

private class KafkaConsumerRegisterer {
    private val mapper = jacksonObjectMapper()

    fun registerSubscribers() {
        DomainSubscriberRegistry.subscribers.forEach { subscriber ->
            waitForMessages(subscriber.subscribedEvent(), subscriber.domainEventName(), subscriber.name()) {
                subscriber.onEvent(it)
            }
        }
    }

    private fun waitForMessages(
        kclass: KClass<out DomainEvent>,
        topic: String,
        subscriberName: String,
        callback: (DomainEvent) -> Unit
    ) = Properties().also {
        it[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "127.0.0.1:9092"
        it[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        it[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        it[ConsumerConfig.GROUP_ID_CONFIG] = subscriberName
        it[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
    }.let {
        KafkaConsumer<String, String>(it)
    }.also {
        it.subscribe(listOf(topic))
    }.also {
        GlobalScope.launch {
            while (true) {
                it.poll(Duration.ofMillis(100)).forEach { record ->
                    callback(mapper.readValue(record.value(), kclass.java))
                }
            }
        }
    }
}

fun useKafka() = KafkaConsumerRegisterer().registerSubscribers()