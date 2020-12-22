package com.home.kafka

import com.home.messagebroker.DomainEvent
import com.home.messagebroker.DomainEventPublisher
import org.apache.kafka.clients.producer.KafkaProducer
import java.net.InetAddress
import java.util.*

class KafkaPublisher : DomainEventPublisher {
    override fun publish(event: DomainEvent) {
        val config = Properties()
        config["client.id"] = InetAddress.getLocalHost().hostName
        config["bootstrap.servers"] = "host1:9092,host2:9092"
        config["acks"] = "all"
        KafkaProducer<String, String>(config)
    }
}