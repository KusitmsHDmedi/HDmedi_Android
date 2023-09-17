package com.example.hdmedi.retrofit

import android.telecom.Call
import com.example.hdmedi.model.*
import retrofit2.http.Body
import retrofit2.http.GET
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

    //회원가입
    @POST("api/user/signUp")
    fun postSignUp(
        @Header ("Authorization") Authorization : String,
        @Body signUpRequestBody: SignUpRequestBody
    ) : retrofit2.Call<SignUpResponseBody>

    //인증 코드 발급
    @GET("api/user/authCode")
    fun getAuthCode(
        @Header ("Authorization") Authorization : String,
    ) : retrofit2.Call<AuthCodeResponseBody>

    //선생님 로그인
    @POST("api/user/signIn/guest")
    fun postGuestSignIn(
        @Header ("Authorization") Authorization : String,
        ) : retrofit2.Call<GuestSignInResponseBody>

    //설문 조사 제출
    @POST("api/survey")
    fun postSurvey(
        @Header ("Authorization") Authorization : String,
        @Body surveyRequestBody: SurveyRequestBody
        ) : retrofit2.Call<SurveyResponseBody>
}