package com.example.hdmedi.util

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hdmedi.HDmediApplication.Companion.preferences
import com.example.hdmedi.R
import com.example.hdmedi.model.resultData
import com.google.android.material.card.MaterialCardView

class ResultAdapter(private var result : ArrayList<resultData>) : RecyclerView.Adapter<ResultAdapter.ViewHolder> (){
    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        val result_question: TextView = view.findViewById(R.id.questionText)
        val result_answer: TextView = view.findViewById(R.id.rv_item_answer)
        val answerView = view.findViewById<LinearLayout>(R.id.answerView)
        val stroke = view.findViewById<MaterialCardView>(R.id.rv_item_back)
        fun bind(item: resultData){
            result_question.text = item.result_question
            result_answer.text = item.result_anwser
            if(preferences.getString("who", "")=="teacher"){
                answerView.setBackgroundColor(Color.parseColor("#fbf6da"))
                stroke.strokeColor = Color.parseColor("#D4BA32")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_result_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultAdapter.ViewHolder, position: Int) {
        val item = result[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return result.size
    }
}