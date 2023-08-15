package com.example.healthcare.adminpanel.edit

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.healthcare.R
import com.example.healthcare.util.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Objects

class EditFindDoctorActivity : AppCompatActivity() {
    private  lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_find_doctor)

        database = FirebaseDatabase.getInstance().getReference("Admin")
        auth = FirebaseAuth.getInstance()

        val nameET = findViewById<EditText>(R.id.adminEDname)
        val contactET = findViewById<EditText>(R.id.adminEDnumber)
        val locationET = findViewById<EditText>(R.id.adminEDlocation)
        val specializationET = findViewById<EditText>(R.id.adminEDspecialization)
        val feeET = findViewById<EditText>(R.id.adminEDfee)
        val btn = findViewById<Button>(R.id.addDoctorBtn)

        val calendar = Calendar.getInstance()
        val currentDate = SimpleDateFormat("MMM dd yyyy")
        val saveCurrentDate = currentDate.format(calendar.time)

        val currentTime = SimpleDateFormat("HH:MM:ss")
        val saveCurrentTime = currentTime.format(calendar.time)

        val key: String = saveCurrentDate.toString() + saveCurrentTime.toString()



        btn.setOnClickListener{
            val name: String = nameET.text.toString()
            val contact : String = contactET.text.toString()
            val location : String = locationET.text.toString()
            val fee : String = feeET.text.toString()
            val specialization: String = specializationET.text.toString()

            if(name.isEmpty() || contact.isEmpty() || location.isEmpty() || fee.isEmpty() || specialization.isEmpty()){
                toast(applicationContext, "Please fill all the details")
            }else{
                val details = EditDoctorDetailModel(key,name, contact, location, specialization, fee)
                database.child("DDetails").child(key).setValue(details)
                    .addOnCompleteListener{task->
                        if(task.isSuccessful){
                            toast(applicationContext, "data uploaded successfully")
                            startActivity(Intent(applicationContext, AdminActivity::class.java))
                            finish()
                        }else{
                            val error: String = task.exception.toString()
                            toast(applicationContext, "error: " + error)
                        }
                    }

            }
        }


    }
}