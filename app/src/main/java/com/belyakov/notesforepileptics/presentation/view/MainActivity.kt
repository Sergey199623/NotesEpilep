package com.belyakov.notesforepileptics.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.belyakov.notesforepileptics.data.Medicine
import com.belyakov.notesforepileptics.databinding.ActivityMainBinding
import com.belyakov.notesforepileptics.presentation.MedicineAdapter
import com.belyakov.notesforepileptics.presentation.viewModel.MedicineViewModel
import com.belyakov.notesforepileptics.presentation.viewModel.MedicineViewOutput
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewOutput: MedicineViewOutput by viewModel<MedicineViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = MedicineAdapter()
            calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                // Получаем информацию о приступах на выбранный день
                val medicineList = getMedicineListForDate(year, month, dayOfMonth)

                // Обновляем RecyclerView
                (recyclerView.adapter as MedicineAdapter).setData(medicineList)
            }
        }
    }

    private fun getMedicineListForDate(year: Int, month: Int, dayOfMonth: Int): List<Medicine> {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val currentTime = calendar.timeInMillis

        val medicineList = mutableListOf<Medicine>()

        viewOutput.allMedicines.observe(this) { medicines ->
            medicines.forEach { medicine ->
                val time = medicine.reminderTime
                if (time != null && time.timeInMillis == currentTime) {
                    medicineList.add(medicine)
                }
            }
        }

        return medicineList
    }
}