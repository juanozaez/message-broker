package com.home.user.create

import com.home.messagebroker.DomainEventPublisher
import com.home.user.create.domain.UserCreator

class UserCreationContext(private var publisher: DomainEventPublisher) {
    lateinit var userCreator: UserCreator
    fun configureContext() {
        userCreator = UserCreator(publisher)
    }
}