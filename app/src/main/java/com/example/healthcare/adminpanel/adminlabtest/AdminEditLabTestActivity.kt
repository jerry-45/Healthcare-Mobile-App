package com.example.healthcare.adminpanel.adminlabtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.healthcare.R
import com.example.healthcare.adminpanel.AdminMainActivity
import com.example.healthcare.util.toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar

class AdminEditLabTestActivity : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_edit_lab_test)

        dbref = FirebaseDatabase.getInstance().getReference("LabTest")

        val etpackName = findViewById<TextView>(R.id.etPackageName)
        val etprice = findViewById<TextView>(R.id.etLabTestPrice)
        val etdesc = findViewById<TextView>(R.id.etLabTestDesc)
        val btn = findViewById<Button>(R.id.btn_Edit_admin_lab_test)




        val calendar = Calendar.getInstance()
        val currentDate = SimpleDateFormat("MMM dd yyyy")
        val saveCurrentDate = currentDate.format(calendar.time)

        val currentTime = SimpleDateFormat("HH:MM:ss")
        val saveCurrentTime = currentTime.format(calendar.time)

        val labkey: String = saveCurrentDate.toString() + saveCurrentTime.toString()

        btn.setOnClickListener{
            val name = etpackName.text.toString()
            val price = etprice.text.toString()
            val desc = etdesc.text.toString()

            val details = LabTestModel(labkey, name, price, desc)

            if(name.isEmpty() || price.isEmpty() || desc.isEmpty()){
                toast(applicationContext, "Please fill all required input fields")
            }else{
                dbref.child(labkey).setValue(details)
                    .addOnCompleteListener{task ->
                        if(task.isSuccessful){
                            toast(applicationContext, "Details uploaded successfully")
                            startActivity(Intent(applicationContext, AdminMainActivity::class.java))
                            finish()
                        }else{
                            val error : String = task.exception.toString()
                            toast(applicationContext, "error : " + error)
                        }
                    }
            }

        }


    }
}