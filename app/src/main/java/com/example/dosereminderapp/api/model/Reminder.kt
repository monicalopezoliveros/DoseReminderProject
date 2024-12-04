package com.example.dosereminderapp.api.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Medication::class, // Relationship with Medication
        parentColumns = ["id"], // Primary key in Medication
        childColumns = ["medicationId"], // Field that connects Reminder with Medication
        onDelete = ForeignKey.CASCADE // Delete reminders if medication is removed
    )]
)
data class Reminder(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val medicationId: Int,
    val reminderTime: Long,
    val frequency: String,
    val notes: String? = null
)
