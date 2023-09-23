package com.example.hdmedi.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.example.hdmedi.R
import com.example.hdmedi.ui.main.result.CheckResultActivity
import com.example.hdmedi.databinding.ActivityHomeBinding
import com.example.hdmedi.HDmediApplication
import com.example.hdmedi.ui.test.MainActivity
import com.example.hdmedi.util.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //정보 세팅
        val name = HDmediApplication.preferences.getString("childrenName", "")
        val birth = HDmediApplication.preferences.getString("birthday", "")
        val gender = HDmediApplication.preferences.getString("gender", "")
        val parentName = HDmediApplication.preferences.getString("userName", "")


        if(gender == "man") {
            binding.gender.text = "남자아이"

        }else if (gender == "woman") {
            binding.gender.text = "여자아이"
        }
        binding.nameText.text = name
        binding.birth.setText(birth)
        binding.detailText.setText("${parentName}의 자녀")

        initGenderImage(gender)


      
        //자가진단 받으러 가기
        binding.runTestButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //이전 자가진단 확인
        binding.checkResultButton.setOnClickListener {
            val intent = Intent(this, CheckResultActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initGenderImage(gender: String){
        if(gender=="woman"){
            binding.childIcon.setBackgroundDrawable(getDrawable(R.drawable.ic_girl))
        }else if(gender=="man"){
            binding.childIcon.setBackgroundDrawable(getDrawable(R.drawable.ic_child))
        }
    }
}