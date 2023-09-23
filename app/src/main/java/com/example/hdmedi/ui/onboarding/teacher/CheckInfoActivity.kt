package com.example.hdmedi.ui.onboarding.teacher

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityCheckInfoBinding
import com.example.hdmedi.ui.test.MainActivity
import com.example.hdmedi.util.base.BaseActivity

class CheckInfoActivity : BaseActivity<ActivityCheckInfoBinding>(R.layout.activity_check_info){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTitleText()
        initInfo()

        //yes btn
        binding.yesButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        //no btn
        binding.noButton.setOnClickListener {
            finish()
        }
    }

    private fun initTitleText(){
        val greenSpan = ForegroundColorSpan(Color.parseColor("#2BAE76"))
        val textData = binding.titleText.text.toString()

        binding.titleText.text = SpannableStringBuilder(textData).apply {
            setSpan(greenSpan, 8, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    private fun initInfo(){
        binding.nameText.text = preferences.getString("childrenName", "")
        binding.birthText.text = preferences.getString("birthday", "")
        binding.detailText.text = preferences.getString("userName", "") + "의 자녀"
        val gender = preferences.getString("gender", "")
        if(gender=="man"){
            binding.genderText.text = "남자 아이"
            binding.childIcon.setBackgroundResource(R.drawable.ic_child)
        }else if(gender=="woman"){
            Log.d("testtt", "여자여자")
            binding.genderText.text = "여자 아이"
            binding.childIcon.setBackgroundResource(R.drawable.ic_girl)
        }
    }
}