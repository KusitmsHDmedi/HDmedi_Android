package com.example.hdmedi.ui.test.result.sendResult

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivitySendResultBinding
import com.example.hdmedi.HDmediApplication
import com.example.hdmedi.ui.main.HomeActivity
import com.example.hdmedi.util.base.BaseActivity

class SendResultActivity : BaseActivity<ActivitySendResultBinding>(R.layout.activity_send_result) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initButton()
        initResultText()
    }

    private fun initButton(){
        binding.button.setOnClickListener {
            when(HDmediApplication.preferences.getString("who","")){
                "parent" -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(this)
                    }
                    finish()
                }

                "teacher" -> {

                }
            }
        }
    }

    private fun initResultText(){
        val greenSpan = ForegroundColorSpan(Color.parseColor("#2BAE76"))
        val name = HDmediApplication.preferences.getString("childrenName", "")
        when(HDmediApplication.preferences.getString("who","")){
            "parent" -> {
                binding.resultText.text = name + "의 자가진단을\n선생님께 요청했습니다!"
            }
            "teacher" -> {
                binding.resultText.text = name + "의 검사결과를\n부모님께 전달하였습니다!"
                binding.detailText.text = "아이에게 관심을 가지고 조사에 응해주신\n 선생님께 진심으로 감사드립니다"
            }
        }
        val textData = binding.resultText.text.toString()
        binding.resultText.text = SpannableStringBuilder(textData).apply {
            setSpan(greenSpan, 0, name.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}