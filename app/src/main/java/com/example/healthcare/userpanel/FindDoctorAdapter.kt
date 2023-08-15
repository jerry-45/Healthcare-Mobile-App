package com.example.healthcare.userpanel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare.R
import com.example.healthcare.adminpanel.edit.EditDoctorDetailModel

class FindDoctorAdapter(val context: Context, val list: ArrayList<EditDoctorDetailModel>): RecyclerView.Adapter<FindDoctorAdapter.myViewHolder>() {


    inner class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.fdItemName)
        val contact = itemView.findViewById<TextView>(R.id.fdItemContact)
        val location = itemView.findViewById<TextView>(R.id.fdItemLocation)
        val fee = itemView.findViewById<TextView>(R.id.fdItemFee)
        val specialization = itemView.findViewById<TextView>(R.id.fdItemspecialization)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.find_doctor_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model: EditDoctorDetailModel = list.get(position)
        holder.name.text = model.name
        holder.contact.text = model.phone
        holder.fee.text ="fees" + model.fees
        holder.location.text = model.location
        holder.specialization.text = model.specialization
    }

}