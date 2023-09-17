package com.example.hdmedi.model

data class GuestSignInResponseBody(
    val status: Int,
    val message: String,
    val data: ChildInfo

)

data class ChildInfo(
    val childName: String,
    val parentsName: String,
    val birthday: String,
    val accessToken: String,
    val gender: String
)