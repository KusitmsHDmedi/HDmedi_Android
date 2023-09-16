package com.example.hdmedi.model

data class SurveyRequestBody(
    val questionList : MutableList<String>,
    val question : String,
    val score : Int,
    val totalScore : Int
)
