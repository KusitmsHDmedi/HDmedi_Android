package com.example.hdmedi.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityHomeBinding
import com.example.hdmedi.sharedPreference.MyApplication

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //정보 세팅
        val name = MyApplication.preferences.getString("name", "")
        val birth = MyApplication.preferences.getString("birth", "")
        val gender = MyApplication.preferences.getString("gender", "")

        initGenderImage(gender)

        binding.nameText.text = name
        binding.birth.text = birth
        binding.gender.text = gender
        binding.detailText.text = "${name} 엄마의 자녀"

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

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initGenderImage(gender: String){
        if(gender=="여자 아이"){
            binding.childIcon.setBackgroundDrawable(getDrawable(R.drawable.ic_girl))
        }else if(gender=="남자 아이"){
            binding.childIcon.setBackgroundDrawable(getDrawable(R.drawable.ic_child))
        }
    }
}