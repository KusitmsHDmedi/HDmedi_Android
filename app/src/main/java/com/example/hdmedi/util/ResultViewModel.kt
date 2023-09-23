package com.example.hdmedi.util

import androidx.lifecycle.ViewModel


class ResultViewModel : ViewModel() {
    var viewModelScoreList = mutableListOf<Int>()
    var viewModelAnswerList = mutableListOf<String>()
    var viewModelscore = 0

    fun addviewModelAnswerList(a : String) {
        viewModelAnswerList.add(a)
    }

    fun addviewModelScoreList(c : Int) {
        viewModelScoreList.add(c)
    }

    fun addviewModelscore(b : Int) {
        viewModelscore += b
    }
}