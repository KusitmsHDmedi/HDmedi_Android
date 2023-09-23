package com.example.hdmedi.ui.main.result

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityCheckResultBinding
import com.example.hdmedi.model.SurveyAll
import com.example.hdmedi.model.SurveyAllResponseBody
import com.example.hdmedi.ui.main.HomeActivity
import com.example.hdmedi.util.ItemClick
import com.example.hdmedi.util.SurveyAllAdapter
import com.example.hdmedi.util.SurveyAllViewModel
import com.example.hdmedi.util.base.BaseActivity


class CheckResultActivity : BaseActivity<ActivityCheckResultBinding>(R.layout.activity_check_result) {

    private lateinit var surveyAllAdapter: SurveyAllAdapter
    private lateinit var viewModel : SurveyAllViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val a =SurveyAll(ArrayList())
        viewModel = ViewModelProvider(this)[SurveyAllViewModel::class.java]

        surveyAllAdapter = SurveyAllAdapter(SurveyAllResponseBody(0,"",a))
        binding.rvSurveyAll.adapter = surveyAllAdapter
        binding.rvSurveyAll.layoutManager = LinearLayoutManager(this)

        viewModel.getSurveyAll()
        viewModel.surveyAllList.observe(this) { result ->
            surveyAllAdapter = SurveyAllAdapter(result)
            binding.rvSurveyAll.adapter = surveyAllAdapter
            surveyAllAdapter.setOnItemClickListener(object: ItemClick {
                override fun onClick(view: View, position: Int) {
                    val data = viewModel.surveyAllList.value!!.data.allSurveyList[position].surveyId.toString()
                    val intent = Intent(this@CheckResultActivity, DetailResultActivity::class.java)
                    intent.putExtra("value", data)
                    startActivity(intent)
                }
            })
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}