package com.example.viikkotehtv1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viikkotehtv1.data.model.TaskEntity
import com.example.viikkotehtv1.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider



class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _allTasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val allTasks: StateFlow<List<TaskEntity>> = _allTasks.asStateFlow()

    private val _uiTasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val uiTasks: StateFlow<List<TaskEntity>> = _uiTasks.asStateFlow()

    private var showDoneOnly = false
    private var sortByTitle = true
        private set

    init {
        viewModelScope.launch {
            repository.allTasks.collect { list ->
                _allTasks.value = list
                applyFiltersAndSort()
            }
        }
    }

    fun addTask(title: String, description: String, dueDate: String) {
        viewModelScope.launch {
            repository.insert(
                TaskEntity(
                    title = title,
                    description = description,
                    dueDate = dueDate
                )
            )
        }
    }

    fun toggleDone(task: TaskEntity) {
        viewModelScope.launch {
            repository.update(task.copy(done = !task.done))
        }
    }

    fun removeTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.update(task)
        }
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

        class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return TaskViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }