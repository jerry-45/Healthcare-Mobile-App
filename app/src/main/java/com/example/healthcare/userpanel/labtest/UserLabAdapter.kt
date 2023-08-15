package com.example.healthcare.userpanel.labtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare.R
import com.example.healthcare.adminpanel.adminlabtest.LabTestModel

class UserLabAdapter(val list: ArrayList<LabTestModel>, val context: Context): RecyclerView.Adapter<UserLabAdapter.myViewHolder>() {

    inner class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.orderItemName)
        val contact = itemView.findViewById<TextView>(R.id.orderItemPrice)
        val price = itemView.findViewById<TextView>(R.id.orderItemDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.multi_line_xml, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model: LabTestModel = list.get(position)
        holder.name.text = "Package " + model.name
        holder.contact.text = "Contact - " + model.contact
        holder.price.text = "Price - " + model.price
    }
}