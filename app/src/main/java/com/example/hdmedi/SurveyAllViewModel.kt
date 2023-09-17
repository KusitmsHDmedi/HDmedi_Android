package com.example.hdmedi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hdmedi.model.list
import com.example.hdmedi.retrofit.APIS
import com.example.hdmedi.retrofit.RetrofitInstance
import com.example.hdmedi.sharedPreference.MyApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SurveyAllViewModel : ViewModel(){

    private lateinit var API : APIS

    private var _surveyAllList = MutableLiveData<ArrayList<list>>()
    var surveyAllList : LiveData<ArrayList<list>> = _surveyAllList

    init {
        getSurveyAll()
    }

    fun getSurveyAll() {
        API = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        val accessToken = MyApplication.preferences.getString("accessToken", "")

        try{
            API.getSurveyAll("Bearer $accessToken").enqueue(
                object : Callback<ArrayList<list>> {

                    override fun onResponse(call: Call<ArrayList<list>>, response: Response<ArrayList<list>>) {
                        if (response.isSuccessful) {

                            _surveyAllList.value = response.body()
                            Log.d("getSurveyAll  : ", "success  , ${response.body().toString()} , ${response.message()}, ${response.errorBody().toString()}")


                        } else {

                            Log.d("getSurveyAll Response : ", "fail 1 , ${response.body().toString()} , ${response.message()}, ${response.errorBody().toString()}")
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<list>>, t: Throwable) {
                        Log.d("getSurveyAll Response : ", " fail 2 , ${t.message.toString()}")
                    }
                })
        } catch (e:Exception) {
            Log.d("getSurveyAll response : ", " fail 3 , ${e.message}")
        }

    }
}

