package com.home.rabbitmq

abstract class DomainSubscriber<in T> {
    abstract fun on(event: DomainEvent)
    abstract fun queue(): String
}