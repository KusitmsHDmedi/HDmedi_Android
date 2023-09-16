package com.example.hdmedi.activity

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityCheckInfoBinding

class CheckInfoActivity : BaseActivity<ActivityCheckInfoBinding>(R.layout.activity_check_info){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTitleText()
    }

    private fun initTitleText(){
        val greenSpan = ForegroundColorSpan(Color.parseColor("#2BAE76"))
        val textData = binding.titleText.text.toString()

        binding.titleText.text = SpannableStringBuilder(textData).apply {
            setSpan(greenSpan, 8, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}