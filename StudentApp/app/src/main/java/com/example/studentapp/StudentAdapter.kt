package com.example.studentapp
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class StudentAdapter(
    private val students: List<Student>,
    private val onItemClick: (Student) -> Unit,
    private val onCheckChanged: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.student_image)
        val nameTextView: TextView = view.findViewById(R.id.student_name)
        val idTextView: TextView = view.findViewById(R.id.student_id)
        val checkBox: CheckBox = view.findViewById(R.id.student_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        try {
            holder.imageView.setImageResource(student.imageUrl)
        } catch (e: Exception) {
            // Fallback to a default color or shape if image loading fails
            holder.imageView.setImageResource(R.drawable.default_student_image)
        }

        holder.nameTextView.text = student.name
        holder.idTextView.text = student.id
        holder.checkBox.isChecked = student.isChecked

        holder.itemView.setOnClickListener { onItemClick(student) }
        holder.checkBox.setOnClickListener { onCheckChanged(student) }
    }

    override fun getItemCount() = students.size
}