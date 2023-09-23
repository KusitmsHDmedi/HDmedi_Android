package com.example.hdmedi.model

data class SurveyResponse(
    val status: Int,
    val message: String,
    val data: CompareSurveyData
)

data class CompareSurveyData(
    val parentsSurveyList: List<SurveyItem>,
    val teacherSurveyList: List<SurveyItem>
)

data class SurveyItem(
    val question: String,
    val score: Int
)