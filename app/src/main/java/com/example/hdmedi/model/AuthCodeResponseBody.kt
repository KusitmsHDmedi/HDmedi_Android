package com.example.hdmedi.model

data class AuthCodeResponseBody(
    val status : Int,
    val message : String,
    val data : AuthCode
)

data class AuthCode(
    val authCode : String
    )
