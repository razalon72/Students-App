package com.example.studentapp

object StudentModel {
    private val studentList = mutableListOf<Student>()

    fun getAllStudents(): List<Student> = studentList

    fun addStudent(student: Student) {
        studentList.add(student)
    }

    fun updateStudent(updatedStudent: Student, position: Int) {
        studentList[position] = updatedStudent
    }

    fun deleteStudent(position: Int) {
        studentList.removeAt(position)
    }

    fun getStudent(position: Int): Student = studentList[position]
}