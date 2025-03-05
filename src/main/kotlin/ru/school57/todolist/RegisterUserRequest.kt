package ru.school57.todolist

data class RegisterUserRequest(
    val email: String,
    val password: String,
    val username: String? = null
)
