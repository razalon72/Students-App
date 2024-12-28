package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {
    private lateinit var studentId: String
    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        studentId = intent.getStringExtra("student_id") ?: return
        setupViews()

        findViewById<Button>(R.id.button_update).setOnClickListener {
            updateStudent()
        }

        findViewById<Button>(R.id.button_delete).setOnClickListener {
            deleteStudent()
        }
    }

    private fun setupViews() {
        nameEditText = findViewById(R.id.edit_name)
        idEditText = findViewById(R.id.edit_id)
        phoneEditText = findViewById(R.id.edit_phone)
        addressEditText = findViewById(R.id.edit_address)

        val student = StudentRepository.getStudent(studentId)
        student?.let {
            nameEditText.setText(it.name)
            idEditText.setText(it.id)
            phoneEditText.setText(it.phone)
            addressEditText.setText(it.address)
        }
    }

    private fun updateStudent() {
        val newName = nameEditText.text.toString()
        val newId = idEditText.text.toString()
        val newPhone = phoneEditText.text.toString()
        val newAddress = addressEditText.text.toString()

        if (newName.isNotEmpty() && newId.isNotEmpty()) {
            val currentStudent = StudentRepository.getStudent(studentId)
            currentStudent?.let { student ->
                val updatedStudent = Student(
                    id = newId,
                    name = newName,
                    phone = newPhone,
                    address = newAddress,
                    isChecked = student.isChecked,
                    imageUrl = student.imageUrl
                )
                StudentRepository.deleteStudent(studentId)
                StudentRepository.addStudent(updatedStudent)

                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        }
    }

    private fun deleteStudent() {
        StudentRepository.deleteStudent(studentId)
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}