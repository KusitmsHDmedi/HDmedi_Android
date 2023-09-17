package com.example.hdmedi.model

import java.time.LocalDate

data class SurveyAllResponseBody(
    val status : Int,
    val message : String,
    val data : SurveyAll
)

data class SurveyAll(
    val allSurveyList : MutableList<list>
)
data class list(
    val date : String,
    val surveyId : Long
)
