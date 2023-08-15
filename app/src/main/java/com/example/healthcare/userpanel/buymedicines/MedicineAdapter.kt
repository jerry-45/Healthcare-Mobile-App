package com.example.healthcare.userpanel.buymedicines

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare.R
import com.example.healthcare.adminpanel.AdminAddMedicine.MedicineModel

class MedicineAdapter(val context: Context, val list: ArrayList<MedicineModel>): RecyclerView.Adapter<MedicineAdapter.myViewHolder>() {

    var onItemClick: ((MedicineModel) -> Unit) ?= null
    inner class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.medName)
        val price = itemView.findViewById<TextView>(R.id.medPrice)
        val cont = itemView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.medicine_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model: MedicineModel = list.get(position)
        holder.name.text = model.name
        holder.price.text = "Rs. " + model.price + " /-"

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(model)
        }
    }
}