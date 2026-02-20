package com.example.viikkotehtv1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viikkotehtv1.data.model.TaskEntity
import com.example.viikkotehtv1.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(vm: TaskViewModel, navigateBack: () -> Unit) {
    val tasks by vm.uiTasks.collectAsState(initial = emptyList())
    var selectedTask by remember { mutableStateOf<TaskEntity?>(null) }

    // Uniikit päivämäärät järjestettynä ja valittu päivä
    val availableDates = tasks.map { it.dueDate }.distinct().sorted()
    var selectedDate by remember { mutableStateOf(availableDates.firstOrNull() ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kalenteri") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Takaisin")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // ------------------- HORISONTAALINEN PÄIVÄMÄÄRÄVALITSIN -------------------------------------------
            Text(
                "Valitse päivä",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(availableDates) { date ->
                    val isSelected = date == selectedDate
                    DateChip(
                        date = date,
                        isSelected = isSelected,
                        onClick = { selectedDate = date }
                    )
                }
            }

            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            // ----------------------- VALITUN PÄIVÄN TEHTÄVÄLISTA ------------------------------------------------
            val filteredTasks = tasks.filter { it.dueDate == selectedDate }

            if (filteredTasks.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Ei tehtäviä tälle päivälle", color = Color.Gray)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(filteredTasks) { task ->
                        TaskCalendarItem(
                            task = task,
                            onToggle = { vm.toggleDone(task) },
                            onClick = { selectedTask = task }
                        )
                    }
                }
            }
        }

        if (selectedTask != null) {
            TaskDetailDialog(
                task = selectedTask!!,
                onSave = { vm.updateTask(it); selectedTask = null },
                onDelete = { vm.removeTask(it); selectedTask = null },
                onDismiss = { selectedTask = null }
            )
        }
    }
}

@Composable
fun DateChip(date: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
    val textColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = date, color = textColor, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TaskCalendarItem(task: TaskEntity, onToggle: () -> Unit, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = task.done, onCheckedChange = { onToggle() })
            Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                Text(task.title, style = MaterialTheme.typography.titleMedium)
                if (task.description.isNotEmpty()) {
                    Text(task.description, style = MaterialTheme.typography.bodySmall, maxLines = 1)
                }
            }
        }
    }
}