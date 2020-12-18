package com.home.user.create.domain

import com.home.rabbitmq.RabbitProducer

class UserCreationContext {
    lateinit var userCreator: UserCreator
    private var publisher = RabbitProducer()
    fun configureContext() {
        userCreator = UserCreator(publisher)
    }
}