package com.example.hdmedi.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.hdmedi.R
import com.example.hdmedi.ui.main.HomeActivity
import com.example.hdmedi.ui.onboarding.OnBoardingActivity
import com.example.hdmedi.HDmediApplication

class SplashActivity : AppCompatActivity() {
    private val splash_delay : Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if(HDmediApplication.preferences.getBoolean("isLogin", false)){
                Intent(this@SplashActivity, HomeActivity::class.java).apply {
                    startActivity(this)
                }
                finish()
            }else{
                Intent(this@SplashActivity, OnBoardingActivity::class.java).apply {
                    startActivity(this)
                }
                finish()
            }
        }, splash_delay)
    }
}