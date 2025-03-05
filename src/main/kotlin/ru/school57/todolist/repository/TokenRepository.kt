package ru.school57.todolist.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.school57.todolist.entity.Token
import ru.school57.todolist.entity.User

interface TokenRepository: JpaRepository<Token, Long> {
    fun findByUser(user: User): List<Token>
}