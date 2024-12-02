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

import com.example.dosereminderapp.components.CustomSpinner

@Composable
fun ConfigReminderScreen(modifier: Modifier, productName: String) {
    var selectedOption by remember { mutableStateOf("Option 1") }
    val options = listOf("Option 1", "Option 2", "Option 3")


    Column(modifier = modifier
        .fillMaxSize()
        .padding(0.dp)
        .background(Color(red = 200, green = 222, blue = 255)),
        //.border(2.dp, Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
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
        // Fila 1
        Row (
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(Color(252,227,220), shape = RoundedCornerShape(8.dp)) // Fondo gris claro y bordes redondeados
                //.border(2.dp, Color.Blue, RoundedCornerShape(8.dp)) // Borde azul y bordes redondeados
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
                    labelSpinner = "Quantity"
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
                    labelSpinner = "Dosage"
                )
            }
        }

        // Fila 2
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
                    labelSpinner = "Frequency"
                )
            }

        }

        // Fila 3
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
                    labelSpinner = "Time"
                )
            }

            Box(modifier = Modifier.weight(2f)) {
                CustomSpinner(
                    items = arrayOf("AM", "PM"),
                    width = 180.dp,
                    backgroundColor = Color.White,
                    fontSize = 18.sp,
                    borderColor = Color(0xFFFFFFFF),
                    labelSpinner = "Day Part"
                )
            }

        }

        // Fila 4
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
                    labelSpinner = "Start to"
                )
            }

        }

        // Fila 5
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
                onClick = { /* Acción del botón */ },
                modifier = Modifier
                    //.background(Color(255, 68, 13))  // Color RGB específico (ejemplo: verde)
                    .width(300.dp)
                    .height(80.dp)// Ancho específico del botón
                    .padding(12.dp),  // Opcional: agregar relleno interno al botón
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(255, 68, 13)  // Color RGB específico (ejemplo: verde)
                ),
                shape = RoundedCornerShape(8.dp) // Radio de las esquinas del botón
            ) {
                Text(
                    text = "Add Reminder",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White  // Color del texto del botón
                    )
                )
            }

        }

    }
}


