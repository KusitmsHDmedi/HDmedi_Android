package com.example.hdmedi.ui.onboarding.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.hdmedi.R
import com.example.hdmedi.ui.onboarding.parent.ParentsSettingActivity
import com.example.hdmedi.databinding.ActivityLoginBinding
import com.example.hdmedi.HDmediApplication
import com.example.hdmedi.util.base.BaseActivity
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLoginButton()
    }

    private fun initLoginButton(){
        binding.naverLoginButton.setOnClickListener {
            NaverIdLoginSDK.initialize(this@LoginActivity, getString(R.string.naver_client_id), getString(R.string.naver_client_secret), "앱 이름")
            NaverIdLoginSDK.authenticate(this@LoginActivity, oAuthLoginCallback)
        }
    }

    private val oAuthLoginCallback = object : OAuthLoginCallback {
        override fun onSuccess() {
            NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
                override fun onSuccess(result: NidProfileResponse) {
                    val naverAccessToken = NaverIdLoginSDK.getAccessToken()

                    Log.e("login", "naverAccessToken : $naverAccessToken")

                    val userName = result.profile!!.name.toString()
                    HDmediApplication.preferences.setString("userName", userName)
                    HDmediApplication.preferences.setString("accessToken", naverAccessToken!!)

                    //화면 이동
                    Intent(this@LoginActivity, ParentsSettingActivity::class.java).apply {
                        startActivity(this)
                    }
                }

                override fun onError(errorCode: Int, message: String) {
                    Log.e("login", message)
                }

                override fun onFailure(httpStatus: Int, message: String) {
                    Log.e("login", message)
                }
            })
        }

        override fun onError(errorCode: Int, message: String) {}

        override fun onFailure(httpStatus: Int, message: String) {}
    }
}