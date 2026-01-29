package com.example.viikkotehtv1.viewmodel


import androidx.lifecycle.ViewModel
import com.example.viikkotehtv1.domain.mockTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.viikkotehtv1.model.Task



class TaskViewModel : ViewModel() {

    private val _allTasks = MutableStateFlow<List<Task>>(mockTasks)
    private val _uiTasks = MutableStateFlow<List<Task>>(mockTasks)
    val tasks: StateFlow<List<Task>> = _uiTasks.asStateFlow()


    private var showDoneOnly = false
    private var sortByTitle = true
        private set
    init {
        applyFiltersAndSort()
    }
    fun addTask(task: Task) {
        _allTasks.value = _allTasks.value + task
        applyFiltersAndSort()
    }

    fun toggleDone(id: Int) {
        _allTasks.value = _allTasks.value.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
        applyFiltersAndSort()
    }

    fun removeTask(id: Int) {
        _allTasks.value = _allTasks.value.filterNot { it.id == id }
        applyFiltersAndSort()
    }

    fun updateTask(updated: Task) {
        _allTasks.value = _allTasks.value.map {
            if (it.id == updated.id) updated else it
        }
        applyFiltersAndSort()
    }

    fun toggleShowDone() {
        showDoneOnly = !showDoneOnly
        applyFiltersAndSort()
    }

    fun toggleSort() {
        sortByTitle = !sortByTitle
        applyFiltersAndSort()
    }

    private fun applyFiltersAndSort() {
        var list = _allTasks.value

        if (showDoneOnly) {
            list = list.filter { it.done }
        }

        list = if (sortByTitle) {
            list.sortedBy { it.title }
        } else {
            list.sortedBy { it.dueDate }
        }

        _uiTasks.value = list
    }
}