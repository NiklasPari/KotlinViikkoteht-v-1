package com.example.viikkotehtv1.model


data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val dueDate: String,
    val done: Boolean
)