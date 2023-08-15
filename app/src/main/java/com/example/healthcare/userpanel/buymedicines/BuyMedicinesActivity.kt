package com.example.healthcare.userpanel.buymedicines

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare.R
import com.example.healthcare.adminpanel.AdminAddMedicine.MedicineModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BuyMedicinesActivity : AppCompatActivity() {
    private lateinit var myadapter: MedicineAdapter
    private lateinit var dbref: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<MedicineModel>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_medicines)

        recyclerView = findViewById(R.id.medRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        dbref = FirebaseDatabase.getInstance().getReference("Medicine")
        list = arrayListOf<MedicineModel>()

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                if(snapshot.exists()){
                    for(details in snapshot.children){
                        val det = details.getValue(MedicineModel::class.java)
                        list.add(det!!)
                    }

                    myadapter = MedicineAdapter(applicationContext, list)
                    recyclerView.adapter = myadapter
                    myadapter.onItemClick = {
                        val intent = Intent(applicationContext, ConfirmMedicine::class.java)
                        intent.putExtra("MedName", it.name)
                        intent.putExtra("MedPrice", it.price)
                        intent.putExtra("MedDesc", it.desc)
                        startActivity(intent)
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}