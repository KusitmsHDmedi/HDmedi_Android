package com.example.hdmedi.activity

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityTeacherSettingBinding

class TeacherSettingActivity : BaseActivity<ActivityTeacherSettingBinding>(R.layout.activity_teacher_setting) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTitleText()
        initEditText()
        initBackButton()
    }

    private fun initBackButton(){
       binding.backButton.setOnClickListener {
           finish()
       }
    }

    private fun initEditText(){
        binding.codeText.setOnFocusChangeListener { view, b ->
            if(!b && binding.codeText.text.isNotEmpty()){
                view.isSelected = true
                if(binding.codeText.text.length==5){
                    binding.nextButton.isActivated = true
                    binding.nextButton.setTextColor(Color.WHITE)
                }
            }else{
                view.isSelected = b
                binding.nextButton.isActivated = false
                binding.nextButton.setTextColor(getColor(R.color.gray700))
            }
        }
    }

    private fun initTitleText(){
        val greenSpan = ForegroundColorSpan(Color.parseColor("#2BAE76"))
        val sizeSpan = RelativeSizeSpan(1.5f)
        val boldSpan = StyleSpan(Typeface.BOLD)
        val textData = binding.titleText.text.toString()

        binding.titleText.text = SpannableStringBuilder(textData).apply {
            setSpan(sizeSpan, 13, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(boldSpan, 13, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(greenSpan, 13, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        binding.codeText.clearFocus()
        return super.dispatchTouchEvent(ev)
    }
}