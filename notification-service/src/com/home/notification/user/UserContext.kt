package com.home.notification.user

import com.home.messagebroker.DomainSubscriberRegistry
import com.home.notification.user.adapter.NotifyUserOnUserCreatedEventSubscriber
import com.home.notification.user.adapter.SendConfirmationToUserOnUserCreatedEventSubscriber
import com.home.notification.user.domain.ConfirmationToUserSender
import com.home.notification.user.domain.UserNotifier

class UserContext {
    fun configureContext() {
        with(DomainSubscriberRegistry) {
            register(NotifyUserOnUserCreatedEventSubscriber(UserNotifier()))
            register(SendConfirmationToUserOnUserCreatedEventSubscriber(ConfirmationToUserSender()))
        }
    }
}