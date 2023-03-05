package com.belyakov.notesforepileptics.domain

import android.app.Application
import com.belyakov.notesforepileptics.data.Medicine
import com.belyakov.notesforepileptics.data.MedicineDao
import com.belyakov.notesforepileptics.data.MedicineDatabase
import kotlinx.coroutines.flow.Flow

class MedicineRepositoryImpl(
    private val applicationContext: Application,
) : MedicineRepository {

    private val medicineDao: MedicineDao

    init {
        val db = MedicineDatabase.getInstance(applicationContext)
        medicineDao = db.medicineDao()
    }

    val allMedicines: Flow<List<Medicine>> = medicineDao.getAllMedicines()

    override suspend fun insert(medicine: Medicine) {
        medicineDao.insert(medicine)
    }

    override suspend fun delete(medicine: Medicine) {
        medicineDao.delete(medicine)
    }

    override suspend fun update(medicine: Medicine) {
        medicineDao.update(medicine)
    }

    override suspend fun getMedicineById(id: Long): Medicine? {
        return medicineDao.getMedicineById(id)
    }
}