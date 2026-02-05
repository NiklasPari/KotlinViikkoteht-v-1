package com.example.viikkotehtv1.ui
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikkotehtv1.domain.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikkotehtv1.viewmodel.TaskViewModel
import com.example.viikkotehtv1.ui.CalendarScreen
import androidx.compose.foundation.background

//tekstien muokkaukseen
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.viikkotehtv1.model.Task


import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(vm: TaskViewModel,
               navigateToCalendar: () -> Unit, //kaikki näytöt mihin halutaan navigoida
               navigateToSettings: () -> Unit ) {

    //määritetään muutama muuttuja
    var newTitle by remember { mutableStateOf("") }
    var newDueDate by remember { mutableStateOf("") }
    var newDescription by remember { mutableStateOf("") }
    val tasks by vm.tasks.collectAsState()
    var selectedTask by remember { mutableStateOf<Task?>(null) }
    var showAddDialog by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {

        //sovelluksen "otsikko"
        Text("Task list" + "\n", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))

        TopAppBar(
            title = { Text("Tasks") },
            actions = {
                // Lisää-nappi (Avaa dialogin)
                IconButton(onClick = { showAddDialog = true }) {
                    Icon(Icons.Filled.Add, contentDescription = "Lisää tehtävä")
                }

                IconButton(onClick = navigateToCalendar) {
                    Icon(Icons.Filled.DateRange, contentDescription = "Calendar")
                }
                IconButton(onClick = navigateToSettings) { // uusi nappi
                    Icon(Icons.Filled.Settings, contentDescription = "Settings")
                }

            }
        )

        //*****************************************************************************************************************
        // Funktioiden napit eli sort ja toggle
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(bottom = 8.dp)) {
            Button(onClick = { vm.toggleSort() }) {
                Text("Sort")
            }
            Button(onClick = { vm.toggleShowDone() }) {
                Text("Show done")
            }

        }


            // *****************TEHTÄVÄN LISÄYS DIALOGI (AlertDialog)******************************
            if (showAddDialog) {
                var newTitle by remember { mutableStateOf("") }
                var newDescription by remember { mutableStateOf("") }
                var newDueDate by remember { mutableStateOf("") }

                AlertDialog(
                    onDismissRequest = { showAddDialog = false },
                    title = { Text("Add new task") },
                    text = {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = newTitle,
                                onValueChange = { newTitle = it },
                                label = { Text("Name") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = newDescription,
                                onValueChange = { newDescription = it },
                                label = { Text("Description") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = newDueDate,
                                onValueChange = { newDueDate = it },
                                label = { Text("Due date (YYYY-MM-DD)") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                if (newTitle.isNotBlank()) {
                                    vm.addTask(
                                        Task(
                                            id = (tasks.maxOfOrNull { it.id } ?: 0) + 1,
                                            title = newTitle,
                                            description = newDescription,
                                            dueDate = newDueDate,
                                            done = false
                                        )
                                    )
                                    showAddDialog = false
                                }
                            }
                        ) {
                            Text("Tallenna")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showAddDialog = false }) {
                            Text("Peruuta")
                        }
                    }
                )
            }

    //********************************* Valmiit täskit ********************************************************************************
        LazyColumn {        //käytetään lazcolumn uusien täskien näyttämiseen
            items(tasks) { task ->

                Card(           //tehdään kortti jossa taskin tiedot on niin erottuu hyvin listassa
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedTask = task }
                        .padding(vertical = 4.dp),


                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        //-------------------------------------------------------------------
                        Row {
                            Checkbox(           //käyetään checkbox että taskien päällä pois laittoon
                                checked = task.done,
                                onCheckedChange = { vm.toggleDone(task.id) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))


                            Text( //taskin tekstit
                                buildAnnotatedString { //luodaaan tekstit niin että niitä voidaan muokata jokaista eriksee
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append(task.title)
                                    }
                                    append("\n") //rivinvaihto
                                    append(task.description)
                                    append("\n") //rivinvaihto
                                    append(task.dueDate)
                                }

                            )
                            Spacer(modifier = Modifier.width(8.dp)) //pieni väli napin ja tekstien väliin

                        }
                    }
                }
            }
        }

                //-------------------------------------------------------------------
        if (selectedTask != null) {
            TaskDetailDialog(
                task = selectedTask!!,
                onSave = {
                    vm.updateTask(it)
                    selectedTask = null
                },
                onDelete = {
                    vm.removeTask(it)
                    selectedTask = null
                },
                onDismiss = { selectedTask = null }
            )
        }
    }
}