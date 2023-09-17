package com.example.hdmedi.model

data class SurveyRequestBody(
    val questionList: List<QuestionList>,
    val totalScore: Int
)

data class QuestionList(
    val question: String,
    val score: Int
)
