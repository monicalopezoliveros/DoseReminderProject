package com.example.dosereminderapp.api.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

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
    val startTime: Long, // Start time of first reminder
    val startDateOption: String, // "today", "tomorrow", or "in_2_days"
    val frequency: String, // Reminder frequency (every 4 hours, etc.)
    val nextReminderTime: Long, // Time for next reminder
    val status: String = "active", //Reminder status
    val createdAt: Long = System.currentTimeMillis(), // Creation date
    val updatedAt: Long = System.currentTimeMillis() // Last Update Date
)