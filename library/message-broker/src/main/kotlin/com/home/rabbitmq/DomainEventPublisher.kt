package com.home.rabbitmq

interface DomainEventPublisher {
    fun publish(event: DomainEvent)
}