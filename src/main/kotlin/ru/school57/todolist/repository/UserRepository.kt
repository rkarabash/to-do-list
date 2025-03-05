package ru.school57.todolist.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.school57.todolist.entity.User

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}
