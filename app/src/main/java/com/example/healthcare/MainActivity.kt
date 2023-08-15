package com.example.healthcare

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.healthcare.authentication.LoginActivity
import com.example.healthcare.userpanel.FindDoctorActivity
import com.example.healthcare.userpanel.buymedicines.BuyMedicinesActivity
import com.example.healthcare.userpanel.confirmorder.ConfirmUserOrder
import com.example.healthcare.userpanel.labtest.LabTestActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val findDoctor = findViewById<CardView>(R.id.cardFindDoctor)
        val exit = findViewById<FloatingActionButton>(R.id.floatingActionButtonExit)
        val labtest = findViewById<CardView>(R.id.cardLabTest)
        val buyMedicine = findViewById<CardView>(R.id.cardBuyMedicine)
        val orderDetails = findViewById<CardView>(R.id.cardOrderDetails)
        auth = FirebaseAuth.getInstance()

        exit.setOnClickListener{
            auth.signOut()
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }

        findDoctor.setOnClickListener{
            startActivity(Intent(applicationContext, FindDoctorActivity::class.java))
        }

        labtest.setOnClickListener{
            startActivity(Intent(applicationContext, LabTestActivity::class.java))
        }

        buyMedicine.setOnClickListener{
            startActivity(Intent(applicationContext, BuyMedicinesActivity::class.java))
        }

        orderDetails.setOnClickListener{
            startActivity(Intent(applicationContext, ConfirmUserOrder::class.java))
        }
    }
}