package com.example.healthcare.userpanel.confirmorder

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare.R
import com.example.healthcare.adminpanel.AdminAddMedicine.MedicineModel
import com.example.healthcare.userpanel.buymedicines.ConfirmMedicine
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ConfirmUserOrder : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference
    private lateinit var myadapter: ConfirmOrderAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<ConfirmOrderModel>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_user_order)
        dbref = FirebaseDatabase.getInstance().getReference("ConfirmOrder")
        recyclerView = findViewById(R.id.confirmUserOrderRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        list = arrayListOf<ConfirmOrderModel>()

        auth = FirebaseAuth.getInstance()
        var user: FirebaseUser? = auth.currentUser
        val userId = user?.uid



        if (userId != null) {
            dbref.child(userId).addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    if(snapshot.exists()){
                        for(details in snapshot.children){
                            val det = details.getValue(ConfirmOrderModel::class.java)
                            list.add(det!!)
                        }
                    }

                    myadapter = ConfirmOrderAdapter(applicationContext, list)
                    recyclerView.adapter = myadapter

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}