package com.example.studentapp

object StudentRepository {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun getStudents(): List<Student> = students.toList()

    fun getStudent(id: String): Student? = students.find { it.id == id }

    fun updateStudent(updatedStudent: Student) {
        val index = students.indexOfFirst { it.id == updatedStudent.id }
        if (index != -1) {
            students[index] = updatedStudent
        }
    }

    fun deleteStudent(id: String) {
        val iterator = students.iterator()
        while (iterator.hasNext()) {
            val student = iterator.next()
            if (student.id == id) {
                iterator.remove()
                break
            }
        }
    }

    fun toggleStudentCheck(id: String) {
        val student = getStudent(id)
        student?.let {
            val updatedStudent = it.copy(isChecked = !it.isChecked)
            updateStudent(updatedStudent)
        }
    }
}