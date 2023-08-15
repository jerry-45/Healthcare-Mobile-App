package com.example.healthcare.authentication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.healthcare.MainActivity
import com.example.healthcare.R
import com.example.healthcare.adminpanel.AdminMainActivity
import com.example.healthcare.util.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val et_email = findViewById<EditText>(R.id.loginEmailAddress)
        val et_password = findViewById<EditText>(R.id.loginPwd)
        val btn = findViewById<Button>(R.id.loginBtn)
        val new_user = findViewById<TextView>(R.id.new_user)
        database = FirebaseDatabase.getInstance().getReference("Admin Details")

        new_user.setOnClickListener{
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
            finish()
        }


        btn.setOnClickListener{
            btn.isEnabled = false
            val email: String = et_email.text.toString()
            val password: String = et_password.text.toString()

            auth = FirebaseAuth.getInstance()


            if(email.isEmpty() || password.isEmpty()){
                toast(applicationContext, "please fill all the input fields")
                btn.isEnabled = true
            }else{
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener{task ->
                        var user: FirebaseUser? = auth.currentUser
                        if(task.isSuccessful){

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
                        }else{
                            toast(applicationContext, "sign in unsucessful")
                        }
                    }
            }
        }
    }
}