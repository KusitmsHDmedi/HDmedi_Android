package com.example.hdmedi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hdmedi.model.SurveyAll
import com.example.hdmedi.model.SurveyAllResponseBody
import com.example.hdmedi.model.list
import com.example.hdmedi.model.resultData
import java.time.LocalDate

class SurveyAllAdapter(private var surveyAll : SurveyAllResponseBody) : RecyclerView.Adapter<SurveyAllAdapter.ViewHolder> (){

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
       return surveyAll.data.allSurveyList.size

    }

    interface ItemClick {
        fun onClick(view: View,position: Int)
    }
    var itemClick : ItemClick? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = surveyAll.data.allSurveyList[position]
        holder.bind(item)

        if(itemClick != null) {
            holder?.itemView!!.setOnClickListener{v ->
                itemClick!!.onClick(v,position)
            }
        }

    }




}

