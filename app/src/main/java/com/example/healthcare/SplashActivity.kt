package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcare.adminpanel.AdminMainActivity
import com.example.healthcare.authentication.LoginActivity
import com.example.healthcare.util.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SplashActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        database = FirebaseDatabase.getInstance().getReference("Admin Details")
        auth = FirebaseAuth.getInstance()
        var user: FirebaseUser? = auth.currentUser

        if(user == null){

            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }else{
            val userId = user.uid
            database.child(userId).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                   if(snapshot.exists()){
                       toast(applicationContext, "snapshot exists")
                       startActivity(Intent(applicationContext, AdminMainActivity::class.java))
                       finish()
                   }else{
                       toast(applicationContext, "snapshot doesn't exists")

                       startActivity(Intent(applicationContext, MainActivity::class.java))
                       finish()
                   }
                }

                override fun onCancelled(error: DatabaseError) {

                }


            })

        }
    }
}