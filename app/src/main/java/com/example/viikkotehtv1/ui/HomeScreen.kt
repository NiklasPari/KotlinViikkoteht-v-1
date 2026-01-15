package com.example.viikkotehtv1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikkotehtv1.domain.*

@Composable
fun HomeScreen() {
    var tasks by remember { mutableStateOf(mockTasks) } // mock-dataa voi muokata
    var showDoneOnly by remember { mutableStateOf(false) }
    var newTitle by remember { mutableStateOf("") }
    var newDesc by remember { mutableStateOf("") }
    var newDueDate by remember { mutableStateOf("") }
    var showAdd by remember { mutableStateOf(false) }
    var sortByTitle by remember { mutableStateOf(true) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Task list", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))

        // Funktioiden napit
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(bottom = 8.dp)) {
            Button(onClick = { showAdd = true }) { Text("Add Task") }
            Button(onClick = {
                showDoneOnly = !showDoneOnly
                tasks = if (showDoneOnly) filterByDone(mockTasks, true) else mockTasks
            }) { Text(if (showDoneOnly) "Show All" else "Show Done") }
            Button(onClick = {
                sortByTitle = !sortByTitle
                tasks = if (sortByTitle) {
                    tasks.sortedBy { it.title.lowercase() }
                } else {
                    tasks.sortedBy { it.dueDate }
                }
            }) {
                Text(if (sortByTitle) "Sort by DueDate" else "Sort A-Z")
            }}



        if (showAdd) {
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                OutlinedTextField(value = newTitle, onValueChange = { newTitle = it }, label = { Text("Title") })
                OutlinedTextField(value = newDesc, onValueChange = { newDesc = it }, label = { Text("Description") })
                OutlinedTextField(value = newDueDate, onValueChange = { newDueDate = it }, label = { Text("DueDate") })
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 4.dp)) {
                    Button(onClick = {
                        if (newTitle.isNotBlank() && newDueDate.isNotBlank()) {
                            tasks = addTask(
                                tasks,
                                Task(
                                    id = (tasks.maxOfOrNull { it.id } ?: 0) + 1,
                                    title = newTitle,
                                    description = newDesc,
                                    dueDate =newDueDate,
                                    done = false
                                )
                            )
                            newTitle = ""
                            newDesc = ""
                            showAdd = false
                        }
                    }) { Text("Add") }

                    Button(onClick = { showAdd = false }) { Text("Cancel") }
                }
            }
        }


        Column {
            tasks.forEach { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(task.title)
                        Text(task.description)
                        Text("Due: ${task.dueDate}")
                        Text(if (task.done) "Done" else "Not Done")
                    }
                    Button(onClick = { tasks = toggleDone(tasks, task.id) }) {
                        Text(if (task.done) "Mark Undone" else "Mark Done")
                    }
                }
            }
        }
    }
}
