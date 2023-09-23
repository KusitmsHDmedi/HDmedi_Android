package com.example.hdmedi.ui.main.result.compare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hdmedi.R

data class Data(val question: String, val parentAnswer: String, val teacherAnswer: String)

class CompareResultAdapter(private val items: ArrayList<Data>): RecyclerView.Adapter<CompareResultAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.result_item, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.question.text = item.question
        holder.parentAnswer.text = item.parentAnswer
        holder.teacherAnswer.text = item.teacherAnswer
    }

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View = v
        val question = view.findViewById<TextView>(R.id.questionText)
        val parentAnswer = view.findViewById<TextView>(R.id.parentAnswer)
        val teacherAnswer = view.findViewById<TextView>(R.id.teacherAnswer)
    }
}