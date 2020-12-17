package com.home.messagebroker

abstract class DomainEvent(open val aggregateId: String) {
    abstract fun name(): String
}