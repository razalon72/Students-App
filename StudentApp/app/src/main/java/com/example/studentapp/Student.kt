package com.example.studentapp

data class Student(
    var id: String,
    var name: String,
    var phone: String = "",
    var address: String = "",
    var isChecked: Boolean = false,
    var imageUrl: Int = R.drawable.default_student_image
)