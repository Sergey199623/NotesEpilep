package com.belyakov.notesforepileptics.presentation.viewModel

import com.belyakov.notesforepileptics.data.Medicine
import com.belyakov.notesforepileptics.presentation.MedicineEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MedicineViewOutput {

    val medicineList: StateFlow<List<Medicine>>
    val medicineEvent: Flow<MedicineEvent>

    fun getMedicineListForDate(year: Int, month: Int, dayOfMonth: Int)

    fun onDeleteMedicine(medicine: Medicine)
}