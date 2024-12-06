package com.example.dosereminderapp.api.model

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class ReminderWithMedicationName(
    @Embedded val reminder: Reminder, // Incluye todos los campos de la entidad Reminder
    @ColumnInfo(name = "medicationName") val medicationName: String // Campo adicional para el nombre del medicamento
)
