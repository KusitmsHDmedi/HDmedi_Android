package com.example.hdmedi

import androidx.lifecycle.ViewModel


class resultViewModel : ViewModel() {


    var viewModelAnswerList = mutableListOf<String>()
    var viewModelscore = 0

    fun addviewModelAnswerList(a : String) {
        viewModelAnswerList.add(a)
    }

    fun addviewModelscore(b : Int) {
        viewModelscore += b
    }



}