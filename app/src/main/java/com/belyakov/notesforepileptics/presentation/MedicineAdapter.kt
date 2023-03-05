package com.belyakov.notesforepileptics.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.belyakov.notesforepileptics.R
import com.belyakov.notesforepileptics.data.Medicine

class MedicineAdapter : RecyclerView.Adapter<MedicineViewHolder>() {
    private var medicineList = emptyList<Medicine>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medicine, parent, false)
        return MedicineViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val medicine = medicineList[position]
        holder.bind(medicine)
    }

    override fun getItemCount(): Int {
        return medicineList.size
    }

    fun setData(data: List<Medicine>) {
        medicineList = data
        notifyDataSetChanged()
    }
}

class MedicineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val medicineName: TextView = itemView.findViewById(R.id.medicineName)
    private val medicineDose: TextView = itemView.findViewById(R.id.medicineDose)
    private val medicineTime: TextView = itemView.findViewById(R.id.medicineTime)

    fun bind(medicine: Medicine) {
        medicineName.text = medicine.name
        medicineDose.text = medicine.dose
        medicineTime.text = medicine.time
    }
}
