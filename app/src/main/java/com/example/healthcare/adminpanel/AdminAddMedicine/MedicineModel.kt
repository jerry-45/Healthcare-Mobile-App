package com.example.healthcare.adminpanel.AdminAddMedicine

data class MedicineModel(
    val medicineKey: String ?= null,
    val name: String ?= null,
    val price: String?= null,
    val desc: String ?= null
)
