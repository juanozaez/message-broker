package com.home.user

import com.home.messagebroker.DomainEvent
import com.home.messagebroker.DomainEventName

@DomainEventName("user.created")
data class UserCreatedEvent(override val aggregateId: String, val name: String) : DomainEvent(aggregateId) {
    override fun name() = "user.created"
}