package com.belyakov.notesforepileptics.presentation

sealed class MedicineEvent {
    object DeletedSuccessfully : MedicineEvent()
    data class Error(val message: String) : MedicineEvent()
}