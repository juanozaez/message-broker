package com.home.rabbitmq

object DomainSubscriberRegistry{

    val subscribers = mutableListOf<DomainSubscriber<*>>()

    fun register(subscriber: DomainSubscriber<*>){
        subscribers.add(subscriber)
    }
}