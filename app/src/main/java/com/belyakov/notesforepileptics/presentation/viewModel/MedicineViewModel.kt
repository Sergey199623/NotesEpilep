package com.belyakov.notesforepileptics.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belyakov.notesforepileptics.data.Medicine
import com.belyakov.notesforepileptics.domain.MedicineRepository
import com.belyakov.notesforepileptics.presentation.MedicineEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MedicineViewModel(
    private val repository: MedicineRepository
) : ViewModel(), MedicineViewOutput {

    private val _medicineList = MutableStateFlow<List<Medicine>>(emptyList())

    override val medicineList: StateFlow<List<Medicine>>
        get() = _medicineList

    private val _medicineEvent = Channel<MedicineEvent>()

    override val medicineEvent: Flow<MedicineEvent>
        get() = _medicineEvent.receiveAsFlow()

    override fun getMedicineListForDate(year: Int, month: Int, dayOfMonth: Int) {
        viewModelScope.launch {
            try {
                val medicineList = repository.getMedicineListForDate(year, month, dayOfMonth)
                _medicineList.emit(medicineList)
            } catch (e: Exception) {
                _medicineEvent.send(MedicineEvent.Error(e.message ?: "An unknown error occurred"))
            }
        }
    }

    override fun onDeleteMedicine(medicine: Medicine) {
        viewModelScope.launch {
            try {
                repository.deleteMedicine(medicine)
                _medicineEvent.send(MedicineEvent.DeletedSuccessfully)
            } catch (e: Exception) {
                _medicineEvent.send(MedicineEvent.Error(e.message ?: "An unknown error occurred"))
            }
        }
    }
}