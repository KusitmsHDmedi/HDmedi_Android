package com.example.hdmedi.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.hdmedi.R
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.naverLoginButton)
        button.setOnClickListener {
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