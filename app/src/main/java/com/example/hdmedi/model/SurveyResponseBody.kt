package com.example.hdmedi.model

data class SurveyResponseBody(
    val status : Int,
    val message : String,
    val data : SurveyResult
)

data class SurveyResult (
    val totalScore : Int,
    val message : String
        )
