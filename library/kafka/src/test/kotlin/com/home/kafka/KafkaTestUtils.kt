package com.home.kafka

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.KafkaAdminClient
import java.util.*

object KafkaTestUtils {
    private val admin = Properties().also {
        it[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = "127.0.0.1:9092"
    }.let {
        KafkaAdminClient.create(it)
    }

    fun deleteTopic(vararg names: String) {
        admin.deleteTopics(admin.listTopics().names().get())
    }

    fun deleteConsumers() {
        admin.deleteConsumerGroups(admin.listConsumerGroups().all().get().map { it.groupId() }.toList())
    }
}
