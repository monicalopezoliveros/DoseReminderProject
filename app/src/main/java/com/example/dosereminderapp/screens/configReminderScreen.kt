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
import androidx.compose.runtime.setValue
import com.example.dosereminderapp.api.model.Medication
import com.example.dosereminderapp.api.model.Reminder

import com.example.dosereminderapp.components.CustomSpinner
import com.example.dosereminderapp.components.CustomSpinner2
import com.example.dosereminderapp.db.AppDatabase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import androidx.navigation.NavController
import com.example.dosereminderapp.destinations.Destination

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
        verticalArrangement = Arrangement.spacedBy(1.dp) // Espaciado uniforme entre filas
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
        // Fila 1: Quantity and Dosage
        Row (
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(Color(252,227,220), shape = RoundedCornerShape(8.dp))
                //.border(2.dp, Color.Blue, RoundedCornerShape(8.dp))
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),  // Espacio entre los elementos
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
                modifier = Modifier.weight(2f) // Usa un peso para que se ajuste dinámicamente
            ) {
                CustomSpinner(
                    items = arrayOf(
                        "Capsule(s)", "Tablet(s)", "Puff(s)", "Drop(s)", "Pill(s)",
                        "Injection(s)", "Patch(s)", "Implant(s)", "Suppository(ies)", "ml"
                    ),
                    width = 180.dp,
                    backgroundColor = Color.White, // Light Yellow,
                    fontSize = 18.sp,
                    borderColor = Color(0xFFFFFFFF),
                    labelSpinner = "Dosage",
                    onItemSelected = { selected -> dosage = selected }
                )
            }
        }

        // Fila 2: Frequency
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

        // Fila 3: Time and Day Part
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

        // Fila 4: Start to
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

        // Fila 5: Button to add reminder
        Row(
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                //.background(Color(240, 240, 240), shape = RoundedCornerShape(8.dp))
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    // Aquí creamos el ID y guardamos el recordatorio
                    val newMedication = Medication(
                        name = productName,
                        quantity = quantity.toInt(),
                        dosage = dosage,
                        frequency = frequency,
                        time = time,
                        dayPart = dayPart,
                        startTo = startTo
                    )
                    println("va a llamar saveMedicationToDatabase")

                    // Guardar el medicamento en la base de datos
                    saveMedicationToDatabase(newMedication, db)

                    // Navegar a la pantalla de PillBoxScreen
                    navController.navigate(Destination.PillBox.route)// Navegar a la pantalla PillBoxScreen
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(80.dp)// Ancho específico del botón
                    .padding(12.dp),  // relleno interno al botón
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

fun saveMedicationToDatabase(medication: Medication, db: AppDatabase) {
    // Usamos withContext para mover la operación de base de datos a un hilo en segundo plano
    kotlinx.coroutines.GlobalScope.launch {
        withContext(Dispatchers.IO) {
            db.medicationDao().insertMedication(medication)
            println("ya llamo  a saveMedicationToDatabase")
        }
    }
}


