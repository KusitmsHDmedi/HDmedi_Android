package com.example.hdmedi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hdmedi.model.resultData

class ResultAdapter(private var result : ArrayList<resultData>) : RecyclerView.Adapter<ResultAdapter.ViewHolder> (){

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        var result_question : TextView
        var result_answer : TextView



        init{
            result_question = view.findViewById(R.id.questionText)
            result_answer = view.findViewById(R.id.rv_item_answer)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_result_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultAdapter.ViewHolder, position: Int) {
       holder.result_question.text = result[position].result_question
       holder.result_answer.text = result[position].result_anwser
    }

    override fun getItemCount(): Int {
        return result.size
    }
}