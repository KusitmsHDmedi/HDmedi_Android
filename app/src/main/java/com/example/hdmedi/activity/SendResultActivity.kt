package com.example.hdmedi.activity

import android.content.Intent
import android.os.Bundle
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivitySendResultBinding

class SendResultActivity : BaseActivity<ActivitySendResultBinding>(R.layout.activity_send_result) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initButton()
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
}