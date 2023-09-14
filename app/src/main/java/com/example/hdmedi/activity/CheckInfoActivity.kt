package com.example.hdmedi.activity

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityCheckInfoBinding

class CheckInfoActivity : BindingActivity<ActivityCheckInfoBinding>(R.layout.activity_check_info){
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