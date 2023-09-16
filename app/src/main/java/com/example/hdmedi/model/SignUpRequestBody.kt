package com.example.hdmedi.model

data class SignUpRequestBody(
    val userName : String,
    val childrenName : String,
    val birthday : String,
    val gender : String,
    val platform : String,
)
