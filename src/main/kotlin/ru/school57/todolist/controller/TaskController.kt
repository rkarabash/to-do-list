package ru.school57.todolist.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.school57.todolist.dto.CreateTaskResponse
import ru.school57.todolist.request.CreateTaskRequest
import ru.school57.todolist.service.TaskService


@RestController
@RequestMapping("/api/v1/tasks")
class TaskController (
    private val taskService: TaskService
) {

    @PostMapping("/create")
    fun createTask(@RequestBody request: CreateTaskRequest, @RequestHeader("Authorization") token: String): CreateTaskResponse {
        val task = taskService.createTask(request, token)
        return CreateTaskResponse (
            title = task.title,
            description = task.description,
            createdAt = task.createdAt,
            updatedAt = task.updatedAt
        )
    }
}