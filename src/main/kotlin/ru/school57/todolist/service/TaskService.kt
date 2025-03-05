package ru.school57.todolist.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import ru.school57.todolist.entity.Task
import ru.school57.todolist.exception.UnAuthorisedException
import ru.school57.todolist.repository.TaskRepository
import ru.school57.todolist.repository.TokenRepository
import ru.school57.todolist.request.CreateTaskRequest

@Service
class TaskService (
    private val tokenRepository: TokenRepository,
    private val taskRepository: TaskRepository
)
{
    @Transactional
    fun createTask(request: CreateTaskRequest, token: String) : Task {
        val checkToken = tokenRepository.findByValue(token)

        if (checkToken == null) {
            throw UnAuthorisedException("Невалидный токен")
        }

        return taskRepository.save(
            Task (
                user = checkToken.user,
                title = request.title,
                description = request.description
            )
        )

    }
}