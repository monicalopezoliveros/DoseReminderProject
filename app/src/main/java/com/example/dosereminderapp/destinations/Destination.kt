package com.example.dosereminderapp.destinations

import androidx.navigation.compose.ComposeNavigator

sealed class Destination (val route: String){
    object Today: Destination("Today")
    object PillBox: Destination("Pill Box")
    object AddMedicine: Destination("Add")
}