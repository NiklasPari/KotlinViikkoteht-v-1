package com.example.viikkotehtv1.data.local

import androidx.room.*
import com.example.viikkotehtv1.data.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks ORDER BY dueDate ASC") // Hae tehtävät
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Insert // Lisää tehtävä
    suspend fun insert(task: TaskEntity)

    @Update // Päivitä tehtävä
    suspend fun update(task: TaskEntity)

    @Delete // Poista tehtävä
    suspend fun delete(task: TaskEntity)
}