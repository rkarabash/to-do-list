package ru.school57.todolist.controlleradvice

import io.swagger.v3.oas.annotations.Hidden
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.school57.todolist.dto.ErrorResponse
import ru.school57.todolist.exception.BadRequestException
import ru.school57.todolist.exception.UnAuthorisedException

@Hidden
@RestControllerAdvice
class GeneralControllerAdvice {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(UnAuthorisedException::class)
    fun unauthorisedExceptionHandler(exception: UnAuthorisedException) : ResponseEntity<ErrorResponse> {
        logger.warn("Не удалось авторизировать пользователя", exception)
        return ResponseEntity.status(401).body(ErrorResponse(exception.message ?: "UNAUTHRISED"))
    }

    @ExceptionHandler(BadRequestException::class)
    fun badRequestExceptionHandler(exception: BadRequestException): ResponseEntity<ErrorResponse> {
        logger.debug(exception.message ?: "BAD REQUEST")
        return ResponseEntity.status(400).body(ErrorResponse(exception.message ?: "BAD REQUEST"))
    }

}