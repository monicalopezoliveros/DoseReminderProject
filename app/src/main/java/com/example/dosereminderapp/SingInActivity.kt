package com.example.dosereminderapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.navigation.compose.rememberNavController
import com.example.dosereminderapp.db.AppDatabase
import com.example.dosereminderapp.screens.SingInScreen
import com.example.dosereminderapp.ui.theme.DoseReminderAppTheme

class SingInActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoseReminderAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val context:Context= applicationContext
                    SingInScreen(modifier = Modifier.padding(innerPadding), context = context)
                }
            }
        }
    }
}