package com.example.healthcare.util

import android.content.Context
import android.widget.Toast

class toast(val context: Context, val message: String){
    init {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}