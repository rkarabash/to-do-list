package ru.school57.todolist.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.school57.todolist.dto.LoginResponce
import ru.school57.todolist.dto.RegisterUserRequest
import ru.school57.todolist.dto.LoginUserRequest
import ru.school57.todolist.dto.ServiceResponse
import ru.school57.todolist.exception.UnAuthorisedException
import ru.school57.todolist.service.AuthService

@RestController
@RequestMapping("api/v1/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/register")
    fun registerUser(@RequestBody request: RegisterUserRequest) =
        ServiceResponse(authService.registerUser(request))

    @PostMapping("/login")
    @Throws(UnAuthorisedException::class)
    fun loginUser(@RequestBody request: LoginUserRequest) = LoginResponce(authService.loginUser(request))
}