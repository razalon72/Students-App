package com.example.studentapp
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var studentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        studentId = intent.getStringExtra("student_id") ?: return
        displayStudentDetails()

        findViewById<Button>(R.id.button_edit).setOnClickListener {
            openEditStudent()
        }
    }

    private fun displayStudentDetails() {
        val student = StudentRepository.getStudent(studentId) ?: return

        findViewById<ImageView>(R.id.student_image).setImageResource(student.imageUrl)
        findViewById<TextView>(R.id.student_name).text = student.name
        findViewById<TextView>(R.id.student_id).text = student.id
        findViewById<TextView>(R.id.student_phone).text =
            if (student.phone.isNotEmpty()) student.phone else "Not provided"
        findViewById<TextView>(R.id.student_address).text =
            if (student.address.isNotEmpty()) student.address else "Not provided"
    }

    private fun openEditStudent() {
        val intent = Intent(this, EditStudentActivity::class.java)
        intent.putExtra("student_id", studentId)
        startActivity(intent)
    }
}