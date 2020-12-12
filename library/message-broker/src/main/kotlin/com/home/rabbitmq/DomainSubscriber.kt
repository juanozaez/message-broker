package com.home.rabbitmq

abstract class DomainSubscriber<T : DomainEvent> {
    abstract fun on(event: Any)
    abstract fun queue(): String
    abstract fun genericClass(): Class<T>
}