package com.example.viikkotehtv1.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikkotehtv1.data.model.TaskEntity

@Composable
fun TaskDetailDialog(
    task: TaskEntity,
    onSave: (TaskEntity) -> Unit,
    onDelete: (TaskEntity) -> Unit,
    onDismiss: () -> Unit
) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    var dueDate by remember { mutableStateOf(task.dueDate) }
    var done by remember { mutableStateOf(task.done) }

    AlertDialog(
        onDismissRequest = onDismiss,
        //Tallenna nappi
        confirmButton = {
            Button(onClick = {
                onSave(
                    task.copy(
                        title = title,
                        description = description,
                        dueDate = dueDate,
                        done = done
                    )
                )
            }) {
                Text("Save")
            }
        },

        //Delete nappi
        dismissButton = {
            Button(onClick = {
                onDelete(task)
            }) {
                Text("Delete")
            }
        },
        title = { Text("Edit task") },
        text = {

            LazyColumn(
                modifier = Modifier.fillMaxSize(), // Täyttää ruudun, jotta skrollaus toimii
                contentPadding = PaddingValues(16.dp), // Lisää tilaa reunoille
                verticalArrangement = Arrangement.spacedBy(8.dp) // Lisää väliä elementtien väliin

            ) {
                item {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                item {
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    OutlinedTextField(
                        value = dueDate,
                        onValueChange = { dueDate = it },
                        label = { Text("Due date") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = done, onCheckedChange = { done = it })
                        Text("Done")
                    }
                }
            }
        }
    )
}