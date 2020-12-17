package com.home.messagebroker

import kotlin.reflect.KClass

abstract class DomainSubscriber<T> where T : DomainEvent {
    abstract fun on(event: T)
    abstract fun subscribedEvent(): KClass<T>
    abstract fun name(): String
    abstract fun toEvent(domainEvent: DomainEvent): T

    // TODO Work around to use specific event in subclasses' calls to on(T)
    fun onEvent(domainEvent: DomainEvent) = on(domainEvent as T)
}