package com.example.viikkotehtv1.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.viikkotehtv1.data.model.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() { // Tietokannan hallinta

    abstract fun taskDao(): TaskDao // DAO-yhteys

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "task_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}