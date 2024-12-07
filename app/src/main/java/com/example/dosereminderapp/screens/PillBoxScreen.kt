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
import androidx.compose.material3.IconButton
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

/**
 * Composable function to display the "Pill Box" screen, which shows a list of medications.
 * It fetches medications from the database and displays them in a list of cards.
 * Each card shows information about the medication, such as name, quantity, dosage, frequency, and time.
 * The user can delete medications from the list by tapping on the delete icon.
 *
 * @param modifier The modifier to be applied to the root composable.
 * @param db The database instance used to fetch and delete medications.
 */
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

        Column(modifier = Modifier.fillMaxSize()) {
            // Add the title text at the top
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Pill Box",  // Título de la página
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(red = 68, green = 140, blue = 252),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))

            // LazyColumn for the list of medications
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)  // Add padding around the list
            ) {
                // Display a card for each medication
                items(medications.value) { medication ->
                    MedicationCard(medication, db) { deletedMedicationId ->
                        medications.value =
                            medications.value.filter { it.id != deletedMedicationId }
                        //MedicationCard(medication, db)
                    }
                }
            }
        }
    }
}

/**
 * Composable function to display a card for each medication in the "Pill Box" screen.
 * The card shows the medication's name, quantity, dosage, frequency, and time.
 * The user can delete the medication by tapping the delete icon.
 *
 * @param medication The medication object to be displayed in the card.
 * @param db The database instance used to delete the medication.
 * @param onDelete A callback function to update the list of medications after deletion.
 */
@Composable
fun MedicationCard(medication: Medication, db: AppDatabase, onDelete: (Int) -> Unit) {
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
            Column(modifier = Modifier.weight(1f)) {
                Text(text = medication.name, fontWeight = FontWeight.Bold)
                Text(text = "${medication.quantity} ${medication.dosage}")
                Text(text = medication.frequency)
                Text(text = medication.time)  // You can format this more if needed
            }

            // X IconButton to delete the medication
            IconButton(
                onClick = {
                    // Coroutine to delete the medication from the database
                    deleteMedication(db, medication.id, onDelete)
                }
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel), // Use the system X icon
                    contentDescription = "Delete Medication",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Red
                )
            }
        }
    }
}

/**
 * Function to delete a medication from the database and update the list of medications.
 *
 * @param db The database instance used to delete the medication.
 * @param medicationId The ID of the medication to be deleted.
 * @param onDelete A callback function to update the UI after the medication is deleted.
 */
fun deleteMedication(db: AppDatabase, medicationId: Int, onDelete: (Int) -> Unit) {
    // Coroutine to delete the medication from the database
    kotlinx.coroutines.GlobalScope.launch {
        db.medicationDao().deleteMedicationById(medicationId)
        // Fetch updated list of medications
        //val updatedMedications = db.medicationDao().getAllMedications()
        // Call the onDelete callback to update the UI
        onDelete(medicationId)
    }
}
