package com.home.user.create

import com.home.rabbitmq.RabbitProducer
import com.home.user.create.domain.UserCreator

class UserCreationContext {
    lateinit var userCreator: UserCreator
    private var publisher = RabbitProducer()
    fun configureContext() {
        userCreator = UserCreator(publisher)
    }
}