package com.home.kafka

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.home.messagebroker.DomainEvent
import com.home.messagebroker.DomainEventPublisher
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.net.InetAddress
import java.util.*

class KafkaPublisher : DomainEventPublisher {
    private val mapper = jacksonObjectMapper()
    private val producer = setUpProducer()

    override fun publish(event: DomainEvent) {
        ProducerRecord<String, String>(event.name(), mapper.writeValueAsString(event)).let {
            producer.send(it)
            producer.flush()
        }
    }

    private fun setUpProducer() = Properties().also {
        it[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "127.0.0.1:9092"
        it[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        it[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        it[ProducerConfig.CLIENT_ID_CONFIG] = InetAddress.getLocalHost().hostName
    }.let {
        KafkaProducer<String, String>(it)
    }
}