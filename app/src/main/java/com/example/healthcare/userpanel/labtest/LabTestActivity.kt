package com.example.healthcare.userpanel.labtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare.R
import com.example.healthcare.adminpanel.adminlabtest.LabTestModel
import com.example.healthcare.adminpanel.edit.AdminActivityAdapter
import com.example.healthcare.adminpanel.edit.EditDoctorDetailModel
import com.example.healthcare.util.toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LabTestActivity : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var myadapter: UserLabAdapter
    private lateinit var list: ArrayList<LabTestModel>

//    val package1: Array<String> = arrayOf("package1 : Full Body Checkup", "", "", "", "999")
//    val package2: Array<String> = arrayOf("Package2 : Blood Glucose Fasting", "", "", "", "299")
//    val package3: Array<String> = arrayOf("package3 : COVID-19 Antibody - IgG", "", "", "", "899")
//    val package4: Array<String> = arrayOf("pacckage4 : Thyroid Check", "", "", "", "499")
//    val package5: Array<String> = arrayOf("pacckage5 : Immunity Check", "", "", "", "699")
//
//    val arr = arrayOf(package1, package2, package3, package4, package5)
//
//    val package_details : Array<String> = arrayOf(
//        "Blood Glucose Fasting \n" +
//                "Complete Hemogram \n" +
//                "HBA1c \n" +
//                "Iron studies \n" +
//                "Kidney function test \n" +
//                "LDH lactase dehydrogenase, serun \n" +
//                "Lipid profile \n" +
//                "Liver function test",
//        "Blood glucose fasting",
//        "COVID-19 Antibody - IgG",
//        "Thyroid profile-Total (T3, T4 & Tsh Ultra-sensitive",
//        "Complete Hemogram \n" +
//                "CRP (C reactive protein) contitative, serun\n" +
//                "Iron studies \n" +
//                "Kidney function test \n" +
//                "Vitamin-D Total-25 Hydroxy \n" +
//                "Liver function test \n" +
//                "Lipid Profile"
//    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test)

        val recyclerView: RecyclerView = findViewById(R.id.labRecyclerView)
        dbref = FirebaseDatabase.getInstance().getReference("LabTest")
        list = arrayListOf<LabTestModel>()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    list.clear()
                    for(details in snapshot.children) {
                        val det = details.getValue(LabTestModel::class.java)
                        list.add(det!!)
                    }
                }
                myadapter = UserLabAdapter(list, applicationContext)
                recyclerView.adapter = myadapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



    }
}