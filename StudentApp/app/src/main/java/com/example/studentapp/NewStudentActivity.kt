package com.example.studentapp
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewStudentActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        nameEditText = findViewById(R.id.edit_name)
        idEditText = findViewById(R.id.edit_id)
        phoneEditText = findViewById(R.id.edit_phone)
        addressEditText = findViewById(R.id.edit_address)

        findViewById<Button>(R.id.button_save).setOnClickListener {
            saveStudent()
        }
    }

    private fun saveStudent() {
        val name = nameEditText.text.toString()
        val id = idEditText.text.toString()
        val phone = phoneEditText.text.toString()
        val address = addressEditText.text.toString()

        if (name.isNotEmpty() && id.isNotEmpty()) {
            StudentRepository.addStudent(
                Student(
                    id = id,
                    name = name,
                    phone = phone,
                    address = address,
                    imageUrl = R.drawable.default_student_image
                )
            )
            finish()
        }
    }
}
