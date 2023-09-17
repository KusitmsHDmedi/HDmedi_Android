package com.example.hdmedi.model

data class SurveyDetailResponseModel(
    val status : Int,
    val message : String,
    val data : SurveyList
)

data class SurveyList (
   val  parentsScore : String,
   val  parentsMessage : String,
   val  teacherScore : String,
   val  teacherMessage : String,
        )
