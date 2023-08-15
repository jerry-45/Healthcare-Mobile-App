package com.example.healthcare.adminpanel.AdminAddMedicine

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.healthcare.R
import com.example.healthcare.adminpanel.AdminMainActivity
import com.example.healthcare.util.toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar

class AddMedicineActivity : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medicine)

        val etname = findViewById<EditText>(R.id.adminMedicineName)
        val etprice = findViewById<TextView>(R.id.admineMedicinePrice)
        val etdesc = findViewById<TextView>(R.id.adminMedicineDesc)

        val btn = findViewById<Button>(R.id.btnAddMedicine)
        dbref = FirebaseDatabase.getInstance().getReference("Medicine")

        val calendar = Calendar.getInstance()
        val currentDate = SimpleDateFormat("MMM dd yyyy")
        val saveCurrentDate = currentDate.format(calendar.time)

        val currentTime = SimpleDateFormat("HH:MM:ss")
        val saveCurrentTime = currentTime.format(calendar.time)

        val medkey: String = saveCurrentDate.toString() + saveCurrentTime.toString()

        btn.setOnClickListener{
            val name: String = etname.text.toString()
            val price: String = etprice.text.toString()
            val desc: String = etdesc.text.toString()

            val details = MedicineModel(medkey, name, price, desc)

            dbref.child(medkey).setValue(details)
                .addOnCompleteListener { task->
                    if(task.isSuccessful){
                        toast(applicationContext, "Data uploaded successfully")
                        startActivity(Intent(applicationContext, AdminMainActivity::class.java))
                        finish()
                    }else{
                        val error: String = task.exception.toString()
                        toast(applicationContext, "error :"  + error)
                    }
                }

        }
    }
}