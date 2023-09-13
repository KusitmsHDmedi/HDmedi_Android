package com.example.hdmedi.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : BindingActivity<ActivityOnBoardingBinding>(R.layout.activity_on_boarding) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initText()
        initNextButton()
        setButton()
    }

    private fun setButton(){
        binding.parentButton.setOnClickListener{
            it.isSelected = true
            binding.teacherButton.isSelected = false
            binding.nextButton.isActivated = true
            binding.nextButton.setTextColor(Color.WHITE)
        }

        binding.teacherButton.setOnClickListener {
            it.isSelected = true
            binding.parentButton.isSelected = false
            binding.nextButton.isActivated = true
            binding.nextButton.setTextColor(Color.WHITE)
        }
    }

    private fun initNextButton(){
        binding.nextButton.setOnClickListener{
            if(binding.parentButton.isSelected){
                Intent(this, LoginActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

    private fun initText(){
        val graySpan = ForegroundColorSpan(Color.parseColor("#8E95A3"))
        val greenSpan = ForegroundColorSpan(Color.parseColor("#2BAE76"))
        val sizeSpan = RelativeSizeSpan(1.5f)

        var textData = binding.titleText.text.toString()
        binding.titleText.text = SpannableStringBuilder(textData).apply {
            setSpan(graySpan, 0, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(greenSpan, 15, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(sizeSpan, 15, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        textData = binding.parentText.text.toString()
        binding.parentText.text = SpannableStringBuilder(textData).apply {
            setSpan(greenSpan, 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        textData = binding.teacherText.text.toString()
        binding.teacherText.text = SpannableStringBuilder(textData).apply {
            setSpan(greenSpan, 4, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}