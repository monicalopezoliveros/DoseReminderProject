package com.example.dosereminderapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.dosereminderapp.api.model.Medication
import com.example.dosereminderapp.api.model.Reminder

import com.example.dosereminderapp.components.CustomSpinner
import com.example.dosereminderapp.db.AppDatabase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import androidx.navigation.NavController
import com.example.dosereminderapp.destinations.Destination

import java.time.LocalTime
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

import java.util.*

@Composable
fun ConfigReminderScreen(modifier: Modifier, productName: String, db: AppDatabase, navController: NavController) {
    var selectedOption by remember { mutableStateOf("Option 1") }
    val options = listOf("Option 1", "Option 2", "Option 3")

    // Variables for reminder data
    var quantity by remember { mutableStateOf("1") }
    var dosage by remember { mutableStateOf("Capsule(s)") }
    var frequency by remember { mutableStateOf("Once a day") }
    var time by remember { mutableStateOf("8:00") }
    var dayPart by remember { mutableStateOf("AM") }
    var startTo by remember { mutableStateOf("Today") }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(0.dp)
        .background(Color(red = 200, green = 222, blue = 255)),
        //.border(2.dp, Color.Red),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(1.dp) // Uniform spacing between rows
    ){

        Spacer(modifier = Modifier.height(44.dp))
        Text(
            text = "$productName",
                style = TextStyle(
                fontSize = 16.sp
            ),
            modifier = modifier
            .background(Color(252,227,220), shape = RoundedCornerShape(5.dp))
            .padding(14.dp)
            .width(400.dp)
        )
        // Row 1: Quantity and Dosage
        Row (
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(Color(252,227,220), shape = RoundedCornerShape(8.dp))
                //.border(2.dp, Color.Blue, RoundedCornerShape(8.dp))
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),  // Space between elements
            verticalAlignment = Alignment.CenterVertically
        ) {

            //Spacer(modifier = Modifier.height(36.dp))

            Box(
                modifier = Modifier.weight(2f) // Usa un peso para que se ajuste dinámicamente
            ) {
                CustomSpinner(
                    items = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"),
                    width = 160.dp,
                    backgroundColor = Color.White,
                    fontSize = 18.sp,
                    borderColor = Color(0xFFFFFFFF),
                    labelSpinner = "Quantity",
                    onItemSelected = { selected -> quantity = selected }
                )
            }

            Box(
                modifier = Modifier.weight(2f) // Use a weight to make it dynamically adjust
            ) {
                CustomSpinner(
                    items = arrayOf("Tablet(s)", "Capsule(s)", "Puff(s)", "Drop(s)", "Pill(s)", "Injection(s)", "Patch(s)", "Implant(s)", "Suppository(ies)", "ml"),
                    width = 180.dp,
                    backgroundColor = Color.White,
                    fontSize = 18.sp,
                    borderColor = Color(0xFFFFFFFF),
                    labelSpinner = "Dosage",
                    onItemSelected = { selected -> dosage = selected }
                )
            }
        }

        // Row 2: Frequency
        Row(
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(Color(240, 240, 240), shape = RoundedCornerShape(8.dp))
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(26.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(3f)) {
                CustomSpinner(
                    items = arrayOf("Once a day", "Twice a day", "Three times a day", "Every 4 hours", "Every 8 hours", "Every other day", "Twice a week"),
                    width = 300.dp,
                    backgroundColor = Color.White,
                    fontSize = 18.sp,
                    borderColor = Color(0xFFFFFFFF),
                    labelSpinner = "Frequency",
                    onItemSelected = { selected -> frequency = selected }
                )
            }

        }

        // Row 3: Time and Day Part
        Row(
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(Color(252,227,220), shape = RoundedCornerShape(8.dp))
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(2f)) {
                CustomSpinner(
                    items = arrayOf("8:00", "8:30", "9:00", "9:30", "10:00"),
                    width = 160.dp,
                    backgroundColor = Color.White,
                    fontSize = 18.sp,
                    borderColor = Color(0xFFFFFFFF),
                    labelSpinner = "Time",
                    onItemSelected = { selected -> time = selected }
                )
            }

            Box(modifier = Modifier.weight(2f)) {
                CustomSpinner(
                    items = arrayOf("AM", "PM"),
                    width = 180.dp,
                    backgroundColor = Color.White,
                    fontSize = 18.sp,
                    borderColor = Color(0xFFFFFFFF),
                    labelSpinner = "Day Part",
                    onItemSelected = { selected -> dayPart = selected }
                )
            }

        }

        // Row 4: Start to
        Row(
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(Color(240, 240, 240), shape = RoundedCornerShape(8.dp))
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(26.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(3f)) {
                CustomSpinner(
                    items = arrayOf("Today", "Tomorrow", "In 2 Days"),
                    width = 160.dp,
                    backgroundColor = Color.White,
                    fontSize = 18.sp,
                    borderColor = Color(0xFFFFFFFF),
                    labelSpinner = "Start to",
                    onItemSelected = { selected -> startTo = selected }
                )
            }

        }

        // Row 5: Button to add reminder
        Row(
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                //.background(Color(240, 240, 240), shape = RoundedCornerShape(8.dp))
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val coroutineScope = rememberCoroutineScope()

            Button(
                onClick = {
                    coroutineScope.launch {
                        // Calculate startTime
                        val startTime = calculateStartTime(time, dayPart, startTo)

                        // Create the medication
                        val newMedication = Medication(
                            name = productName,
                            quantity = quantity.toInt(),
                            dosage = dosage,
                            frequency = frequency,
                            time = time,
                            dayPart = dayPart,
                            startTo = startTo
                        )

                        // Save the medicine in the database
                        //saveMedicationToDatabase(newMedication, db, startTime)

                        saveMedicationWithReminder(newMedication, db, startTime)

                        //val nextReminder = createNextReminder(newMedication, startTime)

                        //saveReminderToDatabase(nextReminder, db)

                        // Navigate to the PillBoxScreen screen
                        navController.navigate(Destination.PillBox.route)
                    }
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(80.dp)
                    .padding(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(255, 68, 13)
                ),
                shape = RoundedCornerShape(8.dp) // Button Corner Radius
            ) {
                Text(
                    text = "Add Reminder",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White  // Button text color
                    )
                )
            }

        }
    }
}

fun saveMedicationToDatabase(medication: Medication, db: AppDatabase, startTime: Long){
    // withContext is used to move the database operation to a background thread
    kotlinx.coroutines.GlobalScope.launch {
        withContext(Dispatchers.IO) {
            db.medicationDao().insertMedication(medication)
            //val medicationId = db.medicationDao().insertMedication(medication)

           // Crear el primer recordatorio basado en la frecuencia
            //val reminder = createNextReminder(medication, startTime)

            //db.reminderDao().insertReminder(reminder)
            // Retornamos el ID generado

        }
    }
}

fun saveReminderToDatabase(newReminder: Reminder,  db: AppDatabase){
    // withContext is used to move the database operation to a background thread
    kotlinx.coroutines.GlobalScope.launch {
        withContext(Dispatchers.IO) {
            db.reminderDao().insertReminder(newReminder)
        }
    }
}

fun createNextReminder(medication: Medication, startTime: Long): Reminder  {

    val interval = when (medication.frequency) {
        "Once a day" -> 24 * 60 * 60 * 1000L // 24 horas en milisegundos
        "Twice a day" -> 12 * 60 * 60 * 1000L // Cada 12 horas
        "Three times a day" -> 8 * 60 * 60 * 1000L // Cada 8 horas
        "Every 4 hours" -> 4 * 60 * 60 * 1000L
        "Every 8 hours" -> 8 * 60 * 60 * 1000L
        "Every other day" -> 48 * 60 * 60 * 1000L
        else -> 24 * 60 * 60 * 1000L // Default value
    }

    // Calculate the next reminder
    val nextReminderTime = startTime + interval // Add the interval to the startTime to get the following

    return Reminder(
        medicationId = medication.id,
        startTime = startTime,
        startDateOption = medication.startTo.lowercase().replace(" ", "_"),
        frequency = medication.frequency,
        nextReminderTime = nextReminderTime, // The following reminder is saved
        status = "active",
        createdAt = System.currentTimeMillis(),
        updatedAt = System.currentTimeMillis()
    )
}

fun calculateStartTime(time: String, dayPart: String, startTo: String): Long {
    // Create a Calendar object and set the time
    val calendar = Calendar.getInstance()

    // Extract hour and minute from string
    val (hour, minute) = time.split(":").map { it.trim() }
    var hourInt = hour.toInt()

    // Set time to AM/PM
    if (dayPart == "PM" && hourInt != 12) {
        hourInt += 12
    } else if (dayPart == "AM" && hourInt == 12) {
        hourInt = 0
    }

    // Setting the hour and minute on the calendar
    calendar.set(Calendar.HOUR_OF_DAY, hourInt)
    calendar.set(Calendar.MINUTE, minute.toInt())
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    // Determine the base date (Today, Tomorrow, In 2 Days)
    val today = Calendar.getInstance()
    when (startTo) {
        "Today" -> {}
        "Tomorrow" -> calendar.add(Calendar.DAY_OF_YEAR, 1)
        "In 2 Days" -> calendar.add(Calendar.DAY_OF_YEAR, 2)
    }

    return calendar.timeInMillis
}

suspend fun saveMedicationWithReminder(
    medication: Medication,
    db: AppDatabase,
    startTime: Long
) {
    withContext(Dispatchers.IO) {
        // Insert the medication and retrieve your ID
        val medicationId = db.medicationDao().insertMedication(medication)

        // Create and save the first reminder
        val reminder = createNextReminder(
            medication.copy(id = medicationId.toInt()),
            startTime
        )
        db.reminderDao().insertReminder(reminder)
    }
}




