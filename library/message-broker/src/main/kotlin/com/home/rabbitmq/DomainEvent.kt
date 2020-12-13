package com.home.rabbitmq

abstract class DomainEvent(open val aggregateId: String) {
    abstract fun name(): String
}