package com.example.dosereminderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.dosereminderapp.ui.theme.DoseReminderAppTheme

import androidx.navigation.compose.composable
import com.example.dosereminderapp.api.DSManager
import  com.example.dosereminderapp.destinations.Destination
import com.example.dosereminderapp.screens.AddMedicineScreen
import com.example.dosereminderapp.screens.PillBoxScreen
import com.example.dosereminderapp.screens.TodayScreen
import com.example.dosereminderapp.view.BottomNav


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoseReminderAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val navController = rememberNavController()
                    App(navController = navController, modifier= Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(navController: NavController, modifier: Modifier){
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Dose Reminder App")}
            )
        },
        bottomBar = { BottomNav(navController = navController)

        }
    ){ paddingValues ->
        paddingValues.calculateBottomPadding()
        Spacer(modifier = Modifier.padding(10.dp))

        NavHost(navController = navController as NavHostController, startDestination = Destination.Today.route) {
            composable(Destination.Today.route) {
                TodayScreen(modifier= Modifier.padding(paddingValues))
            }
            composable(Destination.PillBox.route) {
                PillBoxScreen(modifier= Modifier.padding(paddingValues))
            }
            composable(Destination.AddMedicine.route) {
                AddMedicineScreen(modifier= Modifier.padding(paddingValues))
            }
            
        }

    }
}