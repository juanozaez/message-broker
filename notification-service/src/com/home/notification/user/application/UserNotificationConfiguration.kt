package com.home.notification.user.application

import com.home.notification.user.adapter.NotifyUserOnUserCreatedEventSubscriber
import com.home.notification.user.adapter.SendConfirmationToUserOnUserCreatedEventSubscriber
import com.home.notification.user.domain.ConfirmationToUserSender
import com.home.notification.user.domain.UserNotifier
import com.home.messagebroker.DomainSubscriberRegistry
import com.home.rabbitmq.RabbitConsumer

class UserNotificationConfiguration {

    fun register(){
        with(DomainSubscriberRegistry){
            register(NotifyUserOnUserCreatedEventSubscriber(UserNotifier()))
            register(SendConfirmationToUserOnUserCreatedEventSubscriber(ConfirmationToUserSender()))
        }

        RabbitConsumer().registerSubscribers()
    }
}