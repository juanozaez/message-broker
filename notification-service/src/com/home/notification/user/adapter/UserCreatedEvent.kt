package com.home.notification.user.adapter

import com.home.rabbitmq.DomainEvent

data class UserCreatedEvent(override val aggregateId: String, val name: String): DomainEvent(aggregateId) {
    override fun name() = "user.created"
}