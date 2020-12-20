package com.home.messagebroker

object DomainSubscriberRegistry {

    val subscribers = mutableListOf<DomainSubscriber<*>>()

    fun register(vararg subscriber: DomainSubscriber<*>) {
        subscribers.addAll(subscriber)
    }
}