package com.example.healthcare.userpanel.buymedicines

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.healthcare.R
import com.example.healthcare.userpanel.confirmorder.ConfirmOrderModel
import com.example.healthcare.util.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar

class ConfirmMedicine : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_medicine)

        val iname = intent.getStringExtra("MedName")
        val iprice = intent.getStringExtra("MedPrice")
        val idesc = intent.getStringExtra("MedDesc")
        dbref = FirebaseDatabase.getInstance().getReference("ConfirmOrder")
        auth = FirebaseAuth.getInstance()
        var user: FirebaseUser? = auth.currentUser
        val userId = user!!.uid

        val name = findViewById<TextView>(R.id.confirmMedName)
        val price = findViewById<TextView>(R.id.confirmMedPrice)
        val desc = findViewById<TextView>(R.id.confirmMedDesc2)

        val btn = findViewById<Button>(R.id.btnConfirmMed)
        val etqty = findViewById<EditText>(R.id.myquantity)

        name.text = "Name : " +iname
        price.text = "Price : "+ iprice
        desc.text = idesc
//


//        val total: Int = Integer.parseInt(iprice) * Integer.parseInt(qty)
//        val result: String = total.toString()
//
//        totalPrice.text = result

        val calendar = Calendar.getInstance()
        val currentDate = SimpleDateFormat("MMM dd yyyy")
        val saveCurrentDate = currentDate.format(calendar.time)

        val currentTime = SimpleDateFormat("HH:MM:ss")
        val saveCurrentTime = currentTime.format(calendar.time)

        val orderkey: String = saveCurrentDate.toString() + saveCurrentTime.toString()
//

        btn.setOnClickListener{
            var myqty = etqty.text.toString()
            if(!myqty.isEmpty()){

                val details = ConfirmOrderModel(userId,iname, iprice,myqty)
                dbref.child(userId).child(orderkey).setValue(details)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            toast(applicationContext, "data uploaded successfully")
                        }else{
                            val error: String = task.exception.toString()
                            toast(applicationContext, error)
                        }
                    }
            }else{
                toast(applicationContext, "Please enter quantity of medicine")
            }
        }

    }
}