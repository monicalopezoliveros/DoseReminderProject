package com.example.dosereminderapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dosereminderapp.R
import com.example.dosereminderapp.db.AppDatabase
import com.example.dosereminderapp.api.model.Reminder
import com.example.dosereminderapp.api.model.ReminderWithMedicationName
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * Composable function to display the "Today" screen, which shows reminders for today.
 * It fetches reminders from the database and displays them in a list.
 * If no reminders are available, a message is shown instead.
 *
 * @param modifier The modifier to be applied to the root composable.
 * @param db The database instance used to fetch reminders.
 */
@Composable
fun TodayScreen(modifier: Modifier, db: AppDatabase) {
    //val reminders = remember { mutableStateOf(listOf<Reminder>()) }
    val reminders = remember { mutableStateOf(listOf<ReminderWithMedicationName>()) }

    LaunchedEffect(Unit) {
        // Get the reminders

        reminders.value = db.reminderDao().getAllRemindersWithMedicationNames()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(red = 200, green = 222, blue = 255))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Today",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(red = 68, green = 140, blue = 252),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            // If there are no reminders, display a message
            if (reminders.value.isEmpty()) {
                Text(
                    text = "No reminders set for today.",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    items(reminders.value) { reminder ->
                        ReminderCard(reminder)
                    }
                }
            }
        }
    }
}

/**
 * Composable function to display a reminder card with medication name and reminder time.
 * This is used in the list of reminders on the "Today" screen.
 *
 * @param reminder The reminder object to be displayed in the card.
 */
@Composable
fun ReminderCard(reminder: ReminderWithMedicationName) {
    // Format reminder time
    val formatter = SimpleDateFormat("h:mm a", Locale.getDefault())
    val timeString = formatter.format(Date(reminder.reminder.startTime))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Column with reminder information
            Column(modifier = Modifier.weight(1f)) {
                // Time to the left
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = timeString,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = " - ${reminder.medicationName}",//
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
