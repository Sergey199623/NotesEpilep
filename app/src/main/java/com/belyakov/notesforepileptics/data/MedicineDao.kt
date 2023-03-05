package com.belyakov.notesforepileptics.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    @Query("SELECT * FROM medicine")
    fun getAllMedicines(): Flow<List<Medicine>>

    @Query("SELECT * FROM medicine WHERE id = :id")
    fun getMedicineById(id: Int): Flow<Medicine>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicine(medicine: Medicine)

    @Delete
    suspend fun deleteMedicine(medicine: Medicine)
}