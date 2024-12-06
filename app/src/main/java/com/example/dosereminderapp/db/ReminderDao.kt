package com.example.dosereminderapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.dosereminderapp.api.model.Reminder
import com.example.dosereminderapp.api.model.ReminderWithMedicationName
import com.example.dosereminderapp.api.model.Medication
@Dao
interface ReminderDao {

    // Insert a new reminder
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: Reminder)

    @Query("SELECT * FROM Reminder")
    suspend fun getAllReminders(): List<Reminder>

    @Query("SELECT Reminder.*, medications.name AS medicationName FROM Reminder INNER JOIN medications ON Reminder.medicationId = medications.id")
    suspend fun getAllRemindersWithMedicationNames(): List<ReminderWithMedicationName>

    // Get a reminder by its ID
    @Query("SELECT * FROM Reminder WHERE id = :id")
    suspend fun getReminderById(id: Int): Reminder?

    // Get all reminders associated with a specific medication
    @Query("SELECT * FROM Reminder WHERE medicationId = :medicationId")
    suspend fun getRemindersForMedication(medicationId: Int): List<Reminder>

    // Delete a reminder by its ID
    @Query("DELETE FROM Reminder WHERE id = :id")
    suspend fun deleteReminderById(id: Int)

    // Delete all reminders associated with a specific medication
    @Query("DELETE FROM Reminder WHERE medicationId = :medicationId")
    suspend fun deleteRemindersForMedication(medicationId: Int)

}
