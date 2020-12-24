package com.home.messagebroker

object DomainSubscriberRegistry {

    val subscribers = mutableListOf<DomainEventSubscriber<*>>()

    fun register(vararg subscriber: DomainEventSubscriber<*>) {
        subscribers.addAll(subscriber)
    }
}