package com.example.dosereminderapp.api.model

import androidx.room.ColumnInfo
import androidx.room.Embedded

/**
 * This data class combines the Reminder entity with an additional medication name.
 * It is used to display reminders along with the corresponding medication name.
 */
data class ReminderWithMedicationName(
    @Embedded val reminder: Reminder, // Includes all fields from the Reminder entity
    @ColumnInfo(name = "medicationName") val medicationName: String
)
