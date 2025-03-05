package ru.school57.todolist.controlleradvice

import io.swagger.v3.oas.annotations.Hidden
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.school57.todolist.dto.ErrorResponse
import ru.school57.todolist.exception.BadRequestException

@Hidden
@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(exception: BadRequestException) =
        ResponseEntity.badRequest().body(ErrorResponse(exception.message ?: "BAD REQUEST"))
}