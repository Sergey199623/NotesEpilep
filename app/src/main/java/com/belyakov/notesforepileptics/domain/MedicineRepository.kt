package com.belyakov.notesforepileptics.domain

import com.belyakov.notesforepileptics.data.Medicine

interface MedicineRepository {

    suspend fun insert(medicine: Medicine)

    suspend fun delete(medicine: Medicine)

    suspend fun update(medicine: Medicine)

    suspend fun getMedicineById(id: Long): Medicine
}