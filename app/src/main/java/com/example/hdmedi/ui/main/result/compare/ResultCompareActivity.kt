package com.example.hdmedi.ui.main.result.compare

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityResultCompareBinding
import com.example.hdmedi.model.SurveyResponse
import com.example.hdmedi.service.RetrofitBuilder
import com.example.hdmedi.ui.main.HomeActivity
import com.example.hdmedi.util.base.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultCompareActivity : BaseActivity<ActivityResultCompareBinding>(R.layout.activity_result_compare) {
    private val retrofitService = RetrofitBuilder.retrofitInstance().create(com.example.hdmedi.service.RetrofitService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initButton()
        getResult()
    }

    private fun initRecyclerView(parentData: MutableList<String>, teacherData: MutableList<String>){
        val item = ArrayList<Data>()
        val question = resources.getStringArray(R.array.question)
        for(i in 0 until 18){
            item.add(Data(question[i], parentData[i], teacherData[i]))
        }
        val adapter = CompareResultAdapter(item)
        binding.recyclerView.adapter = adapter
    }

    private fun getResult(){
        retrofitService.getSurveyCompare("Bearer " + preferences.getString("accessToken", ""), 0).enqueue(object: Callback<SurveyResponse>{
            override fun onResponse(
                call: Call<SurveyResponse>,
                response: Response<SurveyResponse>
            ) {
                val parent = response.body()!!.data.parentsSurveyList
                val teacher = response.body()!!.data.teacherSurveyList
                val parentData = ArrayList<String>()
                val teacherData = ArrayList<String>()

                if(response.isSuccessful){
                    for(i in 0 until 18){
                        when(parent[i].score){
                            0 -> parentData.add("전혀 그렇지 않다")
                            1 -> parentData.add("가끔 그렇다")
                            2 -> parentData.add("자주 그렇다")
                            3 -> parentData.add("매우 자주 그렇다")
                        }
                        when(teacher[i].score){
                            0 -> teacherData.add("전혀 그렇지 않다")
                            1 -> teacherData.add("가끔 그렇다")
                            2 -> teacherData.add("자주 그렇다")
                            3 -> teacherData.add("매우 자주 그렇다")
                        }
                    }

                    initRecyclerView(parentData, teacherData)
                }else{
                    Log.d("testtss", response.message())
                }
            }

            override fun onFailure(call: Call<SurveyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun initButton(){
        binding.button.setOnClickListener {
            Intent(this@ResultCompareActivity, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(this)
            }
            finish()
        }
    }
}