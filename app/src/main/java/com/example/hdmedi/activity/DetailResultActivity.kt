package com.example.hdmedi.activity

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.activity.viewModels
import com.example.hdmedi.R
import com.example.hdmedi.SurveyAllViewModel
import com.example.hdmedi.databinding.ActivityDetailResultBinding
import com.example.hdmedi.sharedPreference.MyApplication

class DetailResultActivity : BaseActivity<ActivityDetailResultBinding>(R.layout.activity_detail_result) {
    private val viewModel : SurveyAllViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBackButton()
        initTitleText()

        val id = intent.getStringExtra("value")
        Log.d("testtt", "receive" + id.toString())
        viewModel.getSurveyResult(id!!.toLong())

        viewModel.surveyDetailList.observe(this) { result ->
            binding.scoreText.text = result.data.parentsScore
            binding.textResult.text = result.data.parentsMessage
            Log.d("testtt", result.data.teacherMessage)
            Log.d("testtt", result.data.teacherScore.toString())
        }
    }

    private fun initBackButton(){
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun initTitleText(){
        val greenSpan = ForegroundColorSpan(Color.parseColor("#2BAE76"))
        val name = MyApplication.preferences.getString("childrenName", "")
        binding.titleText.text = name + " ADHD 자가진단 점수"
        val textData = binding.titleText.text.toString()

        binding.titleText.text = SpannableStringBuilder(textData).apply {
            setSpan(greenSpan, 0, name.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}