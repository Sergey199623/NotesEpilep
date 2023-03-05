package com.belyakov.notesforepileptics

import com.belyakov.notesforepileptics.domain.MedicineRepository
import com.belyakov.notesforepileptics.domain.MedicineRepositoryImpl
import com.belyakov.notesforepileptics.presentation.viewModel.MedicineViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MedicineModule {

    fun create() = module {
        viewModel { MedicineViewModel(get()) }
        single<MedicineRepository> { MedicineRepositoryImpl(get()) }
    }
}