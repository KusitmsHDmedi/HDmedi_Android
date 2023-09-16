package com.example.hdmedi.model

data class SurveyDetailResponseModel(
    val status : Int,
    val message : String,
    val data : SurveyList
)

data class SurveyList (
   val  parentsSurveyList : MutableList<Question>,
   val  teacherSurveyList : MutableList<Question>
        )

data class Question(
    val question : String,
    val score : Int
)