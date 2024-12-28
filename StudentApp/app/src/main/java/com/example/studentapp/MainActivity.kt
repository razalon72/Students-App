package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    private fun setupRecyclerView() {
        adapter = StudentAdapter(
            StudentRepository.getStudents(),
            { student -> openStudentDetails(student) },
            { student -> toggleStudentCheck(student) }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun updateList() {
        adapter = StudentAdapter(
            StudentRepository.getStudents(),
            { student -> openStudentDetails(student) },
            { student -> toggleStudentCheck(student) }
        )
        recyclerView.adapter = adapter
    }

    private fun openStudentDetails(student: Student) {
        val intent = Intent(this, StudentDetailsActivity::class.java)
        intent.putExtra("student_id", student.id)
        startActivity(intent)
    }

    private fun toggleStudentCheck(student: Student) {
        StudentRepository.toggleStudentCheck(student.id)
        updateList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                startActivity(Intent(this, NewStudentActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}