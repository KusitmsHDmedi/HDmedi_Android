package com.example.hdmedi.ui.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hdmedi.R

class MainActivity : AppCompatActivity() {

    val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = manager.beginTransaction()

        //fragment 설정
        val TestStartFragment = TestStartFragment()
        val TestFragment = TestFragment()


        //fragment 초기 화면
        transaction.replace(R.id.frameLayout, TestStartFragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

}