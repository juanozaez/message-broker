package com.home.notification.user.application

import com.home.notification.user.adapter.NotifyUserOnUserCreatedEventSubscriber
import com.home.notification.user.domain.UserNotifier
import com.home.rabbitmq.DomainSubscriberRegistry
import com.home.rabbitmq.RabbitConsumer

class UserNotificationConfiguration {

    fun register(){
        DomainSubscriberRegistry.register(NotifyUserOnUserCreatedEventSubscriber(UserNotifier()))
        RabbitConsumer().registerSubscribers()
    }
}