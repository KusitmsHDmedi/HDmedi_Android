package com.example.hdmedi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hdmedi.model.SurveyAll
import com.example.hdmedi.model.SurveyAllResponseBody
import com.example.hdmedi.model.SurveyDetailResponseModel
import com.example.hdmedi.model.list
import com.example.hdmedi.retrofit.APIS
import com.example.hdmedi.retrofit.RetrofitInstance
import com.example.hdmedi.sharedPreference.MyApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SurveyAllViewModel : ViewModel(){

    private lateinit var API : APIS

    private var _surveyAllList = MutableLiveData<SurveyAllResponseBody>()
    var surveyAllList : LiveData<SurveyAllResponseBody> = _surveyAllList

    private var _surveyDetailList = MutableLiveData<SurveyDetailResponseModel>()
    var surveyDetailList : LiveData<SurveyDetailResponseModel> = _surveyDetailList

//    init {
//        getSurveyAll()
//    }

    fun getSurveyAll() {
        API = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        val accessToken = MyApplication.preferences.getString("accessToken", "")

        try{
            API.getSurveyAll("Bearer $accessToken").enqueue(
                object : Callback<SurveyAllResponseBody> {

                    override fun onResponse(call: Call<SurveyAllResponseBody>, response: Response<SurveyAllResponseBody>) {
                        if (response.isSuccessful) {

                            _surveyAllList.value = response.body()
                            Log.d("getSurveyAll  : ", "success  , ${response.body().toString()} , ${response.message()}, ${response.errorBody().toString()}")


                        } else {

                            Log.d("getSurveyAll Response : ", "fail 1 , ${response.body().toString()} , ${response.message()}, ${response.errorBody().toString()}")
                        }
                    }

                    override fun onFailure(call: Call<SurveyAllResponseBody>, t: Throwable) {
                        Log.d("getSurveyAll Response : ", " fail 2 , ${t.message.toString()}")
                    }
                })
        } catch (e:Exception) {
            Log.d("getSurveyAll response : ", " fail 3 , ${e.message}")
        }

    }

    fun getSurveyResult(a : Long) {
        API = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        val accessToken = MyApplication.preferences.getString("accessToken", "")

        try{
            API.getSurveyResult("Bearer $accessToken", a).enqueue(
                object : Callback<SurveyDetailResponseModel> {

                    override fun onResponse(call: Call<SurveyDetailResponseModel>, response: Response<SurveyDetailResponseModel>) {
                        if (response.isSuccessful) {

                            _surveyDetailList.value = response.body()
                            Log.d("getSurveyResult  : ", "success  , ${response.body().toString()} , ${response.message()}, ${response.errorBody().toString()}")


                        } else {

                            Log.d("getSurveyResult Response : ", "fail 1 , ${response.body().toString()} , ${response.message()}, ${response.errorBody().toString()}")
                        }
                    }

                    override fun onFailure(call: Call<SurveyDetailResponseModel>, t: Throwable) {
                        Log.d("getSurveyResult Response : ", " fail 2 , ${t.message.toString()}")
                    }
                })
        } catch (e:Exception) {
            Log.d("getSurveyResult response : ", " fail 3 , ${e.message}")
        }

    }
}

