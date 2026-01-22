package com.example.viikkotehtv1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viikkotehtv1.domain.Task
import com.example.viikkotehtv1.domain.mockTasks

class TaskViewModel : ViewModel() {
    private var allTasks = mockTasks.toList() //importataan kaikki taskit
    var tasks by mutableStateOf(listOf<Task>())
        private set

    var sortByTitle by mutableStateOf(true)
        private set

    init {
        tasks = mockTasks
        sortTasks()
    }

    fun addTask(task: Task) {
        allTasks = allTasks + task
        tasks = allTasks
        sortTasks()
    }


    fun removeTask(id: Int) {
        allTasks = allTasks.filterNot { it.id == id }
        tasks = allTasks
        sortTasks()
    }

    fun filterByDone(showDoneOnly: Boolean) {  //filteröidään niin että näkyy joko kaikki taskit tai vain donet
        tasks = if (showDoneOnly) allTasks.filter { it.done } else allTasks
        sortTasks()
    }

    fun toggleSort() {
        sortByTitle = !sortByTitle
        sortTasks()
    }
    fun toggleDone(id: Int) {
        allTasks = allTasks.map { if (it.id == id) it.copy(done = !it.done) else it }
        tasks = allTasks
        sortTasks()
    }
    private fun sortTasks() {
        tasks = if (sortByTitle) {
            tasks.sortedBy { it.title }
        } else {
            tasks.sortedBy { it.dueDate }
        }
    }
}