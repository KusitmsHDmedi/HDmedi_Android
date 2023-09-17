package com.example.hdmedi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hdmedi.R
import com.example.hdmedi.SurveyAllViewModel
import com.example.hdmedi.databinding.ActivityDetailResultBinding

class DetailResultActivity : BaseActivity<ActivityDetailResultBinding>(R.layout.activity_detail_result) {

    private lateinit var viewModel : SurveyAllViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getStringExtra("id")


        viewModel.getSurveyResult(id!!.toLong())

        viewModel.surveyDetailList.observe(this, {
            result ->
            binding.scoreText.setText(result.data.parentsMessage)
            binding.textResult.setText(result.data.parentsMessage)
        })


    }
}