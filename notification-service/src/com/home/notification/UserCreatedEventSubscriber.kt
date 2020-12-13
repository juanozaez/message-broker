package com.home.notification

import com.home.rabbitmq.DomainEvent
import com.home.rabbitmq.DomainSubscriber

class UserCreatedEventSubscriber : DomainSubscriber<UserCreatedEvent>() {
    override fun on(event: UserCreatedEvent) {
        println("User notified: ${event.name}")
    }

    override fun name() = "user.created"
    override fun subscribedEvent() = UserCreatedEvent::class
    override fun toEvent(domainEvent: DomainEvent) = domainEvent as UserCreatedEvent
}
