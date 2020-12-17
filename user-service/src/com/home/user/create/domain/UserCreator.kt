package com.home.user.create.domain

import com.home.messagebroker.DomainEventPublisher
import com.home.user.UserCreatedEvent
import java.util.*

class UserCreator(private val publisher: DomainEventPublisher) {

    fun create(name: String) {
        publisher.publish(UserCreatedEvent(UUID.randomUUID().toString(), name))
    }
}