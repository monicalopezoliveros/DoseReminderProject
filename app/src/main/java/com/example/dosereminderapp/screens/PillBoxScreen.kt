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
import com.example.dosereminderapp.api.model.Medication
import kotlinx.coroutines.launch

@Composable
fun PillBoxScreen(modifier: Modifier, db: AppDatabase) {
    // Remember the medications list to display them
    val medications = remember { mutableStateOf(listOf<Medication>()) }

    // Load medications from the database
    LaunchedEffect(Unit) {
        // Launch a coroutine to fetch medications from the database
        medications.value = db.medicationDao().getAllMedications()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(red = 200, green = 222, blue = 255))
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)  // Add padding around the list
        ) {
            // Display a card for each medication
            items(medications.value) { medication ->
                MedicationCard(medication)
            }
        }

    }
}

@Composable
fun MedicationCard(medication: Medication) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(containerColor = Color(red = 255, green = 255, blue = 255))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon on the left
            Icon(
                painter = painterResource(id = R.drawable.ic_capsule),
                contentDescription = "Medicine Icon",
                modifier = Modifier
                    .size(60.dp)
                    .padding(10.dp),
                tint = Color.Unspecified
            )

            // Column with information about the medicine
            Column {
                Text(text = medication.name, fontWeight = FontWeight.Bold)
                Text(text = "${medication.quantity} Tablet(s)")  // Assuming quantity represents tablets
                Text(text = medication.frequency)
                Text(text = medication.time)  // You can format this more if needed
            }
        }
    }
}
