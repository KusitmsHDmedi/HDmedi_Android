package com.example.hdmedi.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivitySendResultBinding
import com.example.hdmedi.sharedPreference.MyApplication

class SendResultActivity : BaseActivity<ActivitySendResultBinding>(R.layout.activity_send_result) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initButton()
        initResultText()
    }

    private fun initButton(){
        binding.button.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(this)
            }
            finish()
        }
    }

    private fun initResultText(){
        val greenSpan = ForegroundColorSpan(Color.parseColor("#2BAE76"))
        val name = MyApplication.preferences.getString("childrenName", "")
        binding.resultText.text = name + "의 자가진단을\n선생님께 요청했습니다!"
        val textData = binding.resultText.text.toString()
        binding.resultText.text = SpannableStringBuilder(textData).apply {
            setSpan(greenSpan, 0, name.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}