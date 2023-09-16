package com.example.hdmedi.model

data class SignUpResponseBody(
    val data: Data,
    val message: String,
    val status: Int
)

data class Data(
    val accessToken: String,
    val children: String,
    val refreshToken: String,
    val userId: Int
)