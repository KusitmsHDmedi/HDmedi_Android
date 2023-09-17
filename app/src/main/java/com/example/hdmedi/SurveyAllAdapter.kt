package com.example.hdmedi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hdmedi.model.list
import com.example.hdmedi.model.resultData
import java.time.LocalDate

class SurveyAllAdapter(private var surveyAll : ArrayList<list>) : RecyclerView.Adapter<SurveyAllAdapter.ViewHolder> (){

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var rv_item_date : TextView = view.findViewById(R.id.text_survey_date)


        fun bind(item : list) {
            rv_item_date.text = item.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_result_item,parent,false)
    return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return surveyAll.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = surveyAll[position]
        holder.bind(item)

    }


}

