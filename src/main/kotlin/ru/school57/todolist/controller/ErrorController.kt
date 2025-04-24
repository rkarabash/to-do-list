package ru.school57.todolist.controller

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.school57.todolist.exception.BadRequestException

@RestController
@RequestMapping("/error")
class ErrorController(
    private val meterRegistry: MeterRegistry
) {
    private val errorCounter = meterRegistry.counter("custom.error", listOf(Tag.of("class", "ru.school57.todolist.controller.ErrorController")))

    @GetMapping
    fun getError() {
        errorCounter.increment()
        throw BadRequestException("ERROR OCCURED")
    }
}