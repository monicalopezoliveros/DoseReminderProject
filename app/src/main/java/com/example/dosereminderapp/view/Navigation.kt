package com.example.dosereminderapp.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dosereminderapp.R

import com.example.dosereminderapp.destinations.Destination


@Composable
fun BottomNav(navController: NavController){
    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination

        val ic_today = painterResource(id = R.drawable.ic_today)
        val ic_pill_box = painterResource(id = R.drawable.ic_pillbox)
        val ic_add = painterResource(id = R.drawable.ic_add)

        NavigationBarItem(
            selected = currentDestination?.route == Destination.Today.route,
            onClick = { navController.navigate(Destination.Today.route){
                popUpTo(Destination.Today.route)
                launchSingleTop = true
            } },
            icon = { Icon(
                painter = ic_today,
                contentDescription = "Today Screen Button",
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp),
                tint = Color.Unspecified) },
            label = {
                Text(
                    text = Destination.Today.route,
                    fontSize = 20.sp,
                    color = Color(red = 68, green = 140, blue = 252))
            }
        )

        NavigationBarItem(
            selected = currentDestination?.route == Destination.PillBox.route,
            onClick = { navController.navigate(Destination.PillBox.route){
                popUpTo(Destination.PillBox.route)
                launchSingleTop = true
            } },
            icon = { Icon(
                painter = ic_pill_box,
                contentDescription = "Today Screen Button",
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp),
                tint = Color.Unspecified) },
            label = {
                Text(
                    text = Destination.PillBox.route,
                    fontSize = 20.sp,
                    color = Color(red = 68, green = 140, blue = 252))
            }
        )

        NavigationBarItem(
            selected = currentDestination?.route == Destination.AddMedicine.route,
            onClick = { navController.navigate(Destination.AddMedicine.route){
                popUpTo(Destination.AddMedicine.route)
                launchSingleTop = true
            } },
            icon = { Icon(
                painter = ic_add,
                contentDescription = "Today Screen Button",
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp),
                tint = Color.Unspecified) },
            label = {
                Text(
                    text = Destination.AddMedicine.route,
                    fontSize = 20.sp,
                    color = Color(red = 68, green = 140, blue = 252))
            }
        )
    }
}