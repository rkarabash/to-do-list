package ru.school57.todolist.request

data class CreateTaskRequest(
    val title: String,
    val description: String? = null,
)
