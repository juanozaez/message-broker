package com.home.notification.user.domain

class ConfirmationToUserSender {
    fun sendConfirmation(user: String) {
        println("Confirmation sent to user: $user")
    }
}