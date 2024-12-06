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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.dosereminderapp.ui.theme.DoseReminderAppTheme

import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dosereminderapp.db.AppDatabase
import  com.example.dosereminderapp.destinations.Destination
import com.example.dosereminderapp.screens.AddMedicineScreen
import com.example.dosereminderapp.screens.PillBoxScreen
import com.example.dosereminderapp.screens.TodayScreen
import com.example.dosereminderapp.screens.ConfigReminderScreen
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

                    //val handwrittenFont = FontFamily(Font(R.font.))

                    // get db instance
                    val db = AppDatabase.getInstance(applicationContext)
                    App(navController = navController, modifier= Modifier.padding(innerPadding), db)

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(navController: NavController, modifier: Modifier, db: AppDatabase){
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Dose Reminder",
                        color = Color(255, 68, 13)
                        //fontFamily = FontFamily.Serif
                    )
                }
            )
        },
        bottomBar = { BottomNav(navController = navController)

        }
    ){ paddingValues ->
        paddingValues.calculateBottomPadding()
        Spacer(modifier = Modifier.padding(10.dp))

        NavHost(navController = navController as NavHostController, startDestination = Destination.Today.route) {
            composable(Destination.Today.route) {
                TodayScreen(modifier= Modifier.padding(paddingValues), db = db)
            }
            composable(Destination.PillBox.route) {
                PillBoxScreen(modifier= Modifier.padding(paddingValues), db = db)
            }
            composable(Destination.AddMedicine.route) {
                AddMedicineScreen(modifier= Modifier.padding(paddingValues), navController = navController)
            }
            composable(
                route = Destination.ConfigReminder.route,
                arguments = listOf(navArgument("productName") { type = androidx.navigation.NavType.StringType })
            ) { backStackEntry ->
                val productName  = backStackEntry.arguments?.getString("productName") ?: ""
                ConfigReminderScreen(
                    modifier = modifier,
                    productName = productName,
                    db = db,
                    navController = navController
                )
            }
            
        }

    }
}