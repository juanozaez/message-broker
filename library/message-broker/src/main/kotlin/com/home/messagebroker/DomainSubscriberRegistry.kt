package com.home.messagebroker

object DomainSubscriberRegistry{

    val subscribers = mutableListOf<DomainSubscriber<*>>()

    fun register(subscriber: DomainSubscriber<*>){
        subscribers.add(subscriber)
    }
}