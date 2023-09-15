package com.example.hdmedi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class resultViewModel : ViewModel() {

    private var _answerArrayList = MutableLiveData<ArrayList<String>>()
    var answerArrayList : LiveData<ArrayList<String>> = _answerArrayList

    private var _TotalScore = MutableLiveData<Int>()
    var TotalScore : LiveData<Int> = _TotalScore
//
//    var answerArrayList : ArrayList<String> = ArrayList()
//
//    var TotalScore :Int = 0

    fun addScore (a : Int) {
        _TotalScore.value = _TotalScore.value?.plus(a)
    }

    fun addArray (b : String) {
        _answerArrayList.value?.add(b)
    }

}