package com.example.dosereminderapp.destinations

import androidx.navigation.compose.ComposeNavigator

/**
 * Destination is a sealed class that defines different navigation destinations in the app.
 * Each destination has a route string used for navigation.
 */
sealed class Destination (val route: String){
    object Today: Destination("Today")
    object PillBox: Destination("Pill Box")
    object AddMedicine: Destination("Add")
    object ConfigReminder : Destination("ConfigReminder/{productName}") {
        fun createRoute(productName: String): String = "ConfigReminder/$productName"
    }
}