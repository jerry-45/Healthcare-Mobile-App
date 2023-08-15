package com.example.healthcare.userpanel.confirmorder

import android.widget.TextView

data class ConfirmOrderModel(
    val uid: String ?= null,
    val name: String? =null,
    val price: String ?= null,
    val qty: String?= null
)
