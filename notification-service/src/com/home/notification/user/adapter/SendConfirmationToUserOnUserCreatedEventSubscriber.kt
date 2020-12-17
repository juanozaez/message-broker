package com.home.notification.user.adapter

import com.home.notification.user.domain.ConfirmationToUserSender
import com.home.notification.user.domain.UserNotifier
import com.home.messagebroker.DomainEvent
import com.home.messagebroker.DomainSubscriber

class SendConfirmationToUserOnUserCreatedEventSubscriber(private val confirmationSender: ConfirmationToUserSender) : DomainSubscriber<UserCreatedEvent>() {
    override fun on(event: UserCreatedEvent) {
        confirmationSender.sendConfirmation(event.name)
    }

    override fun name() = "user.created"
    override fun subscribedEvent() = UserCreatedEvent::class
    override fun toEvent(domainEvent: DomainEvent) = domainEvent as UserCreatedEvent
}
