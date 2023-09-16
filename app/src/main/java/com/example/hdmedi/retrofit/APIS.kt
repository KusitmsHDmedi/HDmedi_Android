package com.example.hdmedi.retrofit

import android.telecom.Call
import com.example.hdmedi.model.SignInRequestBody
import com.example.hdmedi.model.SignInResponseBody
import com.example.hdmedi.model.SignUpRequestBody
import com.example.hdmedi.model.SignUpResponseBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


interface APIS {


    //로그인
    @POST("api/user/signIn")
    fun postNaverToken(
        @Header("Authorization") Authorization : String,
        @Body signInRequestBody: SignInRequestBody
    ): retrofit2.Call<SignInResponseBody>

    @POST("api/user/signUp")
    fun postSignUp(
        @Header ("Authorization") Authorization : String,
        @Body signUpRequestBody: SignUpRequestBody
    ) : retrofit2.Call<SignUpResponseBody>


}