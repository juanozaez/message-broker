package com.home.rabbitmq

interface DomainEventPublisher {

    fun publish(any: Any, queueName: String)
}