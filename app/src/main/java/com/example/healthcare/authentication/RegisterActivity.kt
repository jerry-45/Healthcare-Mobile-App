package com.example.healthcare.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.healthcare.MainActivity
import com.example.healthcare.R
import com.example.healthcare.util.toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        val et_name = findViewById<EditText>(R.id.adminEDname)
        val et_email = findViewById<EditText>(R.id.adminEDlocation)
        val et_pwd = findViewById<EditText>(R.id.adminEDnumber)
        val et_cpwd = findViewById<EditText>(R.id.adminEDspecialization)

        val btn = findViewById<Button>(R.id.addDoctorBtn)
        val existingUser = findViewById<TextView>(R.id.existing_user)


        btn.setOnClickListener{
            btn.isEnabled = false
            val name: String = et_name.text.toString()
            val email: String = et_email.text.toString()
            val password: String = et_pwd.text.toString()
            val confirm_password: String = et_cpwd.text.toString()


            if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirm_password.isEmpty()){
                Toast.makeText(applicationContext, "Please fill all the input fields", Toast.LENGTH_SHORT).show()
                btn.isEnabled = true

            }else{
                if(password.length < 8){
                    toast(applicationContext, "Password length must be 8 characters")
                    btn.isEnabled = true

                }else{
                    if(password.equals(confirm_password)){
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful){
                                    Toast.makeText(applicationContext, "User registered successfully", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(applicationContext, MainActivity::class.java))
                                    finish()
                                }else{
                                    val error: String = task.exception.toString()
                                    Toast.makeText(applicationContext, "error : " + error, Toast.LENGTH_SHORT).show()
                                    btn.isEnabled = true


                                }
                            }
                    }else{
                        toast(applicationContext, "Password doesn't match")
                        btn.isEnabled = true
                    }
                }
            }




        }

        existingUser.setOnClickListener{
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }
}