package com.example.hdmedi.model

data class SurveyResultResponseBody(
    val status : Int,
    val message : String,
    val data : SurveyData
)
data class SurveyData(
    val parentsScore : String,
    val parentsMessage : String,
    val teacherScore : String,
    val teacherMessage : String,

    )