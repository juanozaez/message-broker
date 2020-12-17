package com.home.notification.user.adapter

import com.home.notification.user.domain.UserNotifier
import com.home.messagebroker.DomainEvent
import com.home.messagebroker.DomainSubscriber

class NotifyUserOnUserCreatedEventSubscriber(private val userNotifier: UserNotifier) : DomainSubscriber<UserCreatedEvent>() {
    override fun on(event: UserCreatedEvent) {
        userNotifier.notify(event.name)
    }

    override fun name() = "user.created"
    override fun subscribedEvent() = UserCreatedEvent::class
    override fun toEvent(domainEvent: DomainEvent) = domainEvent as UserCreatedEvent
}
