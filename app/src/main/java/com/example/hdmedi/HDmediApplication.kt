package com.example.hdmedi

import android.app.Application
import com.example.hdmedi.sharedPreference.PreferenceUtil

class HDmediApplication : Application() {
    companion object{
        lateinit var preferences: PreferenceUtil
    }

    override fun onCreate() {
        preferences = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}