package com.example.dosereminderapp.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.dosereminderapp.api.model.Medication
import com.example.dosereminderapp.api.model.Product
import com.example.dosereminderapp.api.model.Reminder

@Database(entities = [Medication::class, Reminder::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // DAO references
    abstract fun medicationDao(): MedicationDao
    abstract fun reminderDao(): ReminderDao

    // Singleton pattern
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "dose_reminder_db"
                )
                    .fallbackToDestructiveMigration() // Avoid migration issues
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
