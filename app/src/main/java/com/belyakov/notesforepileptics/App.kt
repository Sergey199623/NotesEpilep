package com.belyakov.notesforepileptics

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import androidx.multidex.MultiDexApplication

class App : MultiDexApplication(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startDIifNeed(this)
    }

    companion object {

        @Synchronized
        fun startDIifNeed(context: Context) {

            startKoin {
                androidContext(context)
                modules(MedicineModule.create())
            }
        }
    }
}