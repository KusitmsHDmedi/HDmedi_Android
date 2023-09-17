package com.example.hdmedi.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hdmedi.R
import com.example.hdmedi.SurveyAllAdapter
import com.example.hdmedi.SurveyAllViewModel
import com.example.hdmedi.databinding.ActivityCheckResultBinding
import com.example.hdmedi.model.SurveyAll
import com.example.hdmedi.model.SurveyAllResponseBody


class CheckResultActivity : BaseActivity<ActivityCheckResultBinding>(R.layout.activity_check_result) {

    private lateinit var surveyAllAdapter: SurveyAllAdapter
    private lateinit var viewModel : SurveyAllViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val a =SurveyAll(ArrayList())
        viewModel = ViewModelProvider(this).get(SurveyAllViewModel::class.java)

        surveyAllAdapter = SurveyAllAdapter(SurveyAllResponseBody(0,"",a))
        binding.rvSurveyAll.adapter = surveyAllAdapter
        binding.rvSurveyAll.layoutManager = LinearLayoutManager(this)

        viewModel.getSurveyAll()
        viewModel.surveyAllList.observe(this, {
            result->
            surveyAllAdapter = SurveyAllAdapter(result)
            binding.rvSurveyAll.adapter = surveyAllAdapter

        }

        )

//        surveyAllAdapter.itemClick = object : SurveyAllAdapter.ItemClick{
//            override fun onClick(view: View, position: Int) {
//
//               //아이디 넘기기
//               val id = viewModel.surveyAllList.value!!.data.allSurveyList[position].surveyId
//
//
//                Log.d("id", id.toString())
//                Log.d("click", "click")
//
//               val intent = Intent(baseContext, DetailResultActivity::class.java)
//                intent.putExtra("id",id )
//                startActivity(intent)
//            }
//        }

//        surveyAllAdapter.setOnItemClickListener(object : OnItemClickListener {
//            @SuppressLint("ResourceType")
//            override fun onItemClick(v: View, data: SurveyAllResponseBody, pos: Int) {
//
//               val id = viewModel.surveyAllList.value!!.data.allSurveyList[pos].surveyId
//
//
//                Log.d("id", id.toString())
//                Log.d("click", "click")
//
//               val intent = Intent(baseContext, DetailResultActivity::class.java)
//                intent.putExtra("id",id )
//                startActivity(intent)
//            }
//        })


        binding.backButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}