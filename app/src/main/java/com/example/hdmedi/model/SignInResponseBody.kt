package com.example.hdmedi.model

data class SignInResponseBody(
    val message: String,
    val status: Int,
    val data : Token
)

data class Token (
    val accessToken : String,
    val refreshToken : String,
    val userId : Int,
    val children : String

        )