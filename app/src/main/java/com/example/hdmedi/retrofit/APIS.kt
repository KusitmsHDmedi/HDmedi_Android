package com.example.hdmedi.retrofit

import android.telecom.Call
import com.example.hdmedi.model.SignInRequestBody
import com.example.hdmedi.model.SignInResponseBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


interface APIS {


    //로그인
    @POST("api/user/signIn")
    fun postNaverToken(

        @Header("accessToken") accessToken : String
    ): retrofit2.Call<SignInResponseBody>


}