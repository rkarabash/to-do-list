package ru.school57.todolist.service

import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Service
import ru.school57.todolist.entity.Task
import ru.school57.todolist.exception.UnAuthorisedException
import ru.school57.todolist.repository.TaskRepository
import ru.school57.todolist.repository.TokenRepository
import ru.school57.todolist.request.CreateTaskRequest
import kotlin.math.log

@Service
class TaskService(
    private val tokenRepository: TokenRepository,
    private val taskRepository: TaskRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun createTask(request: CreateTaskRequest, token: String): Task {
        logger.debug("Пришел запрос на созадние таска. Request: $request, Request: ${MDC.get("requestId")}")
        val checkToken = tokenRepository.findByValue(token)

        if (checkToken == null) {
            throw UnAuthorisedException("Невалидный токен")
        }

        MDC.put("userId", checkToken.user.id.toString())

        val savedTask = taskRepository.save(
            Task(
                user = checkToken.user,
                title = request.title,
                description = request.description
            )
        )
        logger.info("Создали таск с id = ${savedTask.id}")
        return savedTask
    }
}