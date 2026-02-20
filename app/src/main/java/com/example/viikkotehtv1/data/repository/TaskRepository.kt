package com.example.viikkotehtv1.data.repository

import com.example.viikkotehtv1.data.local.TaskDao
import com.example.viikkotehtv1.data.model.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val taskDao: TaskDao
) {

    val allTasks: Flow<List<TaskEntity>> = // Kaikki tehtävät
        taskDao.getAllTasks()

    suspend fun insert(task: TaskEntity) { // Tehtävän lisäys
        taskDao.insert(task)
    }

    suspend fun update(task: TaskEntity) { // Tehtävän päivitys
        taskDao.update(task)
    }

    suspend fun delete(task: TaskEntity) { // Tehtävän poisto
        taskDao.delete(task)
    }
}