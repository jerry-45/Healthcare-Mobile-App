package com.example.healthcare.userpanel

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare.MainActivity
import com.example.healthcare.R
import com.example.healthcare.adminpanel.edit.AdminActivityAdapter
import com.example.healthcare.adminpanel.edit.EditDoctorDetailModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FindDoctorActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var myadapter: FindDoctorAdapter
    private lateinit var list: ArrayList<EditDoctorDetailModel>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_doctor)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference().child("Admin")
        val recyclerView = findViewById<RecyclerView>(R.id.FindDoctorRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        list = arrayListOf<EditDoctorDetailModel>()

        database.child("DDetails").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    list.clear()
                    for(details in snapshot.children){
                        val det =details.getValue(EditDoctorDetailModel::class.java)
                        list.add(det!!)
                    }
                    myadapter = FindDoctorAdapter(applicationContext, list)
                    recyclerView.adapter = myadapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
}