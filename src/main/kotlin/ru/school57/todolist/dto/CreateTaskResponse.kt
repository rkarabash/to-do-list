package ru.school57.todolist.dto

import java.time.LocalDateTime

data class CreateTaskResponse (
    val title: String,
    val description: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)