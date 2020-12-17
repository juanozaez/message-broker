package com.home.messagebroker

interface DomainEventPublisher {
    fun publish(event: DomainEvent)
}