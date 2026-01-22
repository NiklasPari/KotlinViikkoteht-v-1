package com.example.viikkotehtv1.ui
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

//tekstien muokkaukseen
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun HomeScreen(vm: TaskViewModel = viewModel()) {

    //määritetään muutama muuttuja
    var newTitle by remember { mutableStateOf("") }
    var newDueDate by remember { mutableStateOf("") }
    var newDescription by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        //sovelluksen "otsikko"
        Text("Task list" + "\n", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))

        //*****************************************************************************************************************
        // Funktioiden napit eli sort ja toggle
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(bottom = 8.dp)) {
            Button(onClick = { vm.toggleSort() }) {
                Text(if (vm.sortByTitle) "Sort by date" else "Sort A-Z")
            }

            var showDoneOnly by remember { mutableStateOf(false) }

            Button(onClick = {
                showDoneOnly = !showDoneOnly
                vm.filterByDone(showDoneOnly)
            }) {
                Text(if (showDoneOnly) "Show All" else "Show Done")
            }
        }
        //****************** UUSIEN TASKIN KIRJOITUS FIELDIT ********************************************************************************************
        OutlinedTextField( modifier = Modifier .fillMaxWidth(),

            value = newTitle,
            onValueChange = { newTitle = it },
            label = { Text("Title") }
        )

        OutlinedTextField( modifier = Modifier .fillMaxWidth(),
            value = newDueDate,
            onValueChange = { newDueDate = it },
            label = { Text("Due date") }
        )
        OutlinedTextField( modifier = Modifier .fillMaxWidth(),
            value = newDescription,
            onValueChange = { newDescription = it },
            label = { Text("Description") }
        )

        //**************************** Uudet taskit *************************************************************************************
        Row(modifier = Modifier.padding(bottom = 8.dp)){ //paddin napin alapuolelle selkeämpi erottelu rowien väliin
            Button( modifier = Modifier .fillMaxWidth(),onClick = {
            if (newTitle.isNotBlank()) {
                vm.addTask(
                    Task(
                        id = vm.tasks.size + 1,     //id aina 1 enemmän kuin viimeisin
                        title = newTitle,           //lisätään otsikko
                        description = newDescription,  //lisätään deski
                        dueDate = newDueDate,           //lisätään päivä
                        done = false                    //vakiona ei ole tehty
                    )
                )
                newTitle = ""
                newDueDate = ""
                newDescription = ""
            }
        }) {
            Text("Add task")
        }
    }
    //********************************* Valmiit täskit ********************************************************************************
        LazyColumn {        //käytetään lazcolumn uusien täskien näyttämiseen
            items(vm.tasks) { task ->

                Card(           //tehdään kortti jossa taskin tiedot on niin erottuu hyvin listassa
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ){
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

                        Button(onClick = { vm.removeTask(task.id) },
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp), //napista vähän pienempi
                                modifier = Modifier.height(32.dp)
                        ) { Text("Remove") }
                    }
                }
                //-------------------------------------------------------------------
                }
            }
        }
    }
}