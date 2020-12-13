package com.home.notification

import com.home.rabbitmq.DomainEvent
import com.home.rabbitmq.DomainSubscriber
import com.home.user.UserCreatedEvent

class NotifyUserOnUserCreatedEventSubscriber : DomainSubscriber<UserCreatedEvent>() {
    override fun on(event: UserCreatedEvent) {
        println("User notified: ${event.name}")
    }

    override fun name() = "user.created"
    override fun subscribedEvent() = UserCreatedEvent::class
    override fun toEvent(domainEvent: DomainEvent) = domainEvent as UserCreatedEvent
}
