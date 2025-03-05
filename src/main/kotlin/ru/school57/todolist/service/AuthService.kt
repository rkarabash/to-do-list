package ru.school57.todolist.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import ru.school57.todolist.dto.RegisterUserRequest
import ru.school57.todolist.dto.LoginUserRequest
import ru.school57.todolist.entity.Token
import ru.school57.todolist.entity.User
import ru.school57.todolist.exception.BadRequestException
import ru.school57.todolist.exception.UnAuthorisedException
import ru.school57.todolist.repository.TokenRepository
import ru.school57.todolist.repository.UserRepository
import java.util.*

@Component
class AuthService(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) {
    @Transactional
    fun registerUser(request: RegisterUserRequest): String {
        val user = userRepository.findByEmail(request.email)
        if (user != null) throw BadRequestException("Пользователь уже зарегистрирован")
        userRepository.save(
            User(
                email = request.email,
                password = request.password,
                username = request.username
            )
        )
        return "Пользователь успешно зарегистрирован"
    }

    @Transactional
    fun loginUser(request: LoginUserRequest): String {
        val user = userRepository.findByEmail(request.email)
        if (user == null) {
            throw BadRequestException("Такого пользователя не существует")
        }

        if (user.password != request.password) {
            throw UnAuthorisedException("Неверный логин или пароль")
        }

        val token = tokenRepository.findByUser(user).firstOrNull()
        if (token == null) {
            return tokenRepository.save(
                 Token(
                     value = UUID.randomUUID().toString(),
                     user = user
                 )
             ).value
        }
        return  token.value
    }


}
