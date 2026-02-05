package com.example.viikkotehtv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.viikkotehtv1.ui.theme.Viikkotehtävä1Theme
import com.example.viikkotehtv1.ui.HomeScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.viikkotehtv1.ui.CalendarScreen
import com.example.viikkotehtv1.viewmodel.TaskViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikkotehtv1.ui.SettingsScreen
import androidx.compose.runtime.*

const val ROUTE_HOME = "home"
const val ROUTE_CALENDAR = "calendar"
const val ROUTE_SETTINGS = "settings"


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val vm: TaskViewModel = viewModel()
            val navController = rememberNavController()
            var isDarkTheme by remember { mutableStateOf(false) }
            Viikkotehtävä1Theme(darkTheme = isDarkTheme) {
            NavHost(
                navController = navController,
                startDestination = ROUTE_HOME) {
                composable(ROUTE_HOME) {
                    HomeScreen(
                        vm = vm,
                        navigateToCalendar = { navController.navigate(ROUTE_CALENDAR) },
                        navigateToSettings = { navController.navigate(ROUTE_SETTINGS) }

                    )
                }
                composable(ROUTE_CALENDAR) {
                    CalendarScreen(vm = vm, navigateBack = { navController.popBackStack() })
                }
                composable(ROUTE_SETTINGS) {
                            SettingsScreen(
                                isDarkTheme = isDarkTheme,
                                onThemeToggle = { isDarkTheme = it },
                                navigateBack = { navController.popBackStack() })
                }
            }
        }}
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Viikkotehtävä1Theme {
        Greeting("Android")
    }
}