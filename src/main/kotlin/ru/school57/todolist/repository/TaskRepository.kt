package ru.school57.todolist.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.school57.todolist.entity.Task
import ru.school57.todolist.entity.Token

interface TaskRepository : JpaRepository<Task, Long> {}