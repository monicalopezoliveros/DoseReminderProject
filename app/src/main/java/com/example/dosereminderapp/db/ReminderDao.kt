package com.example.dosereminderapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dosereminderapp.api.model.Reminder

@Dao
interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReminder(reminder: Reminder)

    @Query("SELECT * FROM Reminder WHERE id = :id")
    fun getReminderById(id: Int): Reminder?

    @Query("SELECT * FROM Reminder WHERE medicationId = :medicationId")
    fun getRemindersForMedication(medicationId: Int): List<Reminder>

    @Update
    fun updateReminder(reminder: Reminder)

    @Query("DELETE FROM Reminder WHERE id = :id")
    fun deleteReminderById(id: Int)
}
