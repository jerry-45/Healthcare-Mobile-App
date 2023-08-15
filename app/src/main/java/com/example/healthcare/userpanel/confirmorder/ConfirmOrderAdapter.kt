package com.example.healthcare.userpanel.confirmorder

import android.content.Context
import android.icu.number.IntegerWidth
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare.R
import org.w3c.dom.Text

class ConfirmOrderAdapter(val context: Context, val list: ArrayList<ConfirmOrderModel>): RecyclerView.Adapter<ConfirmOrderAdapter.myViewHolder>() {

    inner class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.orderItemName)
        val price = itemView.findViewById<TextView>(R.id.orderItemPrice)
        val qty = itemView.findViewById<TextView>(R.id.orderItemQty)
        val totalAmt = itemView.findViewById<TextView>(R.id.totalAmt)
        val delete = itemView.findViewById<TextView>(R.id.orderItemDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model : ConfirmOrderModel = list.get(position)
        holder.name.text = model.name
        holder.price.text ="price : " + model.price
        holder.qty.text = "qty : " + model.qty
        val amount : Int = Integer.parseInt(model.price) * Integer.parseInt(model.qty)
        val result : String = amount.toString()
        holder.totalAmt.text = "Total Amt : " +result




    }
}