package com.example.dosereminderapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dosereminderapp.api.model.Medication

@Dao
interface MedicationDao {
    // Insert or replace a medication record
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedication(medication: Medication):Long

    // Get a medicine by its ID
    @Query("SELECT * FROM medications WHERE id = :id")
    suspend fun getMedicationById(id: Int): Medication?

    // Recover all medications
    @Query("SELECT * FROM medications")
    suspend fun getAllMedications(): List<Medication>

    // Update an existing medication
    @Update
    suspend fun updateMedication(medication: Medication)

    // Update an existing medication
    @Query("DELETE FROM medications WHERE id = :id")
    suspend fun deleteMedicationById(id: Int)
}