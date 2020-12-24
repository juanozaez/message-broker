package com.home.notification.user.adapter

import com.home.messagebroker.DomainEvent
import com.home.messagebroker.DomainEventSubscriber
import com.home.notification.user.domain.UserNotifier

class NotifyUserOnUserCreatedEventSubscriber(private val userNotifier: UserNotifier) :
    DomainEventSubscriber<UserCreatedEvent>() {
    override fun on(event: UserCreatedEvent) {
        userNotifier.notify(event.name)
    }

    override fun name() = "notification.notify.user.on.user.created"
    override fun subscribedEvent() = UserCreatedEvent::class
    override fun toEvent(domainEvent: DomainEvent) = domainEvent as UserCreatedEvent
}
