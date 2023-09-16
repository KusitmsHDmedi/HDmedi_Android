package com.example.hdmedi.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.hdmedi.R
import com.example.hdmedi.model.SignInRequestBody
import com.example.hdmedi.model.SignInResponseBody
import com.example.hdmedi.retrofit.APIS
import com.example.hdmedi.retrofit.RetrofitInstance
import com.example.hdmedi.sharedPreference.MyApplication
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class LoginActivity : AppCompatActivity() {


    private val APIS = RetrofitInstance.retrofitInstance().create<APIS>(APIS::class.java)

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

                    val userName = result.profile!!.name.toString()
                    MyApplication.preferences.setString("userName", userName)

                    val naverToken = "Bearer $naverAccessToken"

                    try{
                        APIS.postNaverToken(naverToken, SignInRequestBody("naver")).enqueue(
                            object : Callback<SignInResponseBody> {

                                override fun onResponse(call: Call<SignInResponseBody>, response: Response<SignInResponseBody>) {
                                    if (response.isSuccessful) {

                                        val accessToken = response.body()!!.data.accessToken
                                        val userId = response.body()!!.data.userId

                                        MyApplication.preferences.setString("accessToken", accessToken)
                                        MyApplication.preferences.setString("userId", userId.toString())


                                        Log.d("accessToken", accessToken)

                                        //화면 이동
                                        Intent(this@LoginActivity, ParentsSettingActivity::class.java).apply {
                                            startActivity(this)
                                        }

                                            } else {
                                        Log.d("SignInResponseBody Response : ", " fail 1 , ${response.message()}")
                                          }
                                }

                                override fun onFailure(call: Call<SignInResponseBody>, t: Throwable) {
                                    Log.d("SignInResponseBody Response : ", " fail 2 , ${t.message.toString()}")
                                }
                            })
                    } catch (e:Exception) {
                        Log.d("SignInResponseBody response : ", " fail 3 , ${e.message}")
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