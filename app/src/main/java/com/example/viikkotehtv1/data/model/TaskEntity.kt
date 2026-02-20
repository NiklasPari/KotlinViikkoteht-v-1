package com.example.viikkotehtv1.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks") // Tehtävätaulu
data class TaskEntity(

    @PrimaryKey(autoGenerate = true) // Pääavain
    val id: Int = 0,

    val title: String,

    val description: String,

    val dueDate: String,

    val done: Boolean = false
)