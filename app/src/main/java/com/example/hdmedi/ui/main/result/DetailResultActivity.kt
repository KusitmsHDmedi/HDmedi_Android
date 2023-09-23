package com.example.hdmedi.ui.main.result

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.activity.viewModels
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityDetailResultBinding
import com.example.hdmedi.HDmediApplication
import com.example.hdmedi.ui.main.result.compare.ResultCompareActivity
import com.example.hdmedi.util.SurveyAllViewModel
import com.example.hdmedi.util.base.BaseActivity

class DetailResultActivity : BaseActivity<ActivityDetailResultBinding>(R.layout.activity_detail_result) {
    private val viewModel : SurveyAllViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBackButton()
        initResult()
        initTitleText()
        initNextButton()
    }

    private fun initBackButton(){
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun initResult(){
        val id = intent.getStringExtra("value")
        Log.d("testtt", "receive" + id.toString())
        viewModel.getSurveyResult(id!!.toLong())

        viewModel.surveyDetailList.observe(this) { result ->
            binding.scoreText.text = result.data.parentsScore
            binding.textResult.text = result.data.parentsMessage

            if(result.data.teacherScore!="-1"){
                initTeacherView(result.data.teacherScore, result.data.teacherMessage)
                if(result.data.teacherScore.toInt()>=19){
                    binding.scoreInfo.text = "19점 이상일 경우"
                }else{
                    binding.scoreInfo.text = "19점 미만일 경우"
                }
            }else{
                binding.teacherScoreText.text = "-"
            }
            Log.d("testtt", result.data.teacherMessage)
            Log.d("testtt", result.data.teacherScore)
        }
    }

    private fun initTitleText(){
        val greenSpan = ForegroundColorSpan(Color.parseColor("#2BAE76"))
        val name = HDmediApplication.preferences.getString("childrenName", "")
        binding.titleText.text = "$name ADHD 자가진단 점수"
        val textData = binding.titleText.text.toString()

        binding.titleText.text = SpannableStringBuilder(textData).apply {
            setSpan(greenSpan, 0, name.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    private fun initTeacherView(score: String, result: String){
        binding.teacherScoreText.text = score
        binding.button.apply {
            isActivated = true
            setTextColor(Color.WHITE)
        }
        binding.scoreInfo.setTextColor(Color.BLACK)
        binding.teacherResult.text = result
    }

    private fun initNextButton(){
        binding.button.setOnClickListener {
            if(binding.button.isActivated){
                Intent(this@DetailResultActivity, ResultCompareActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }
}