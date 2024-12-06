package com.example.dosereminderapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dosereminderapp.api.model.Medication

@Dao
interface MedicationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedication(medication: Medication):Long // Inserta o reemplaza un registro de medicamento

    @Query("SELECT * FROM medications WHERE id = :id")
    suspend fun getMedicationById(id: Int): Medication? // Obtiene un medicamento por su ID

    @Query("SELECT * FROM medications")
    suspend fun getAllMedications(): List<Medication> // Recupera todos los medicamentos

    @Update
    suspend fun updateMedication(medication: Medication) // Actualiza un medicamento existente

    @Query("DELETE FROM medications WHERE id = :id")
    suspend fun deleteMedicationById(id: Int) // Elimina un medicamento por su ID
}