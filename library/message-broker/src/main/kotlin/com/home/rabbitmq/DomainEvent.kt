package com.home.rabbitmq

open class DomainEvent(open val aggregateId: String) {
    open fun name(): String = ""
}