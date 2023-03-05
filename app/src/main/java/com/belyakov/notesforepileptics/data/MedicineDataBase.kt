package com.belyakov.notesforepileptics.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Medicine::class], version = 1, exportSchema = false)
abstract class MedicineDatabase : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao

    companion object {
        private const val DATABASE_NAME = "medicine_database"

        private var instance: MedicineDatabase? = null

        fun getInstance(context: Context): MedicineDatabase {
            if (instance == null) {
                synchronized(MedicineDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MedicineDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return instance as MedicineDatabase
        }
    }
}