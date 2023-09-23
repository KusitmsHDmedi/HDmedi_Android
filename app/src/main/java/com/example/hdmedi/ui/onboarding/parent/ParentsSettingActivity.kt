package com.example.hdmedi.ui.onboarding.parent

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.example.hdmedi.R
import com.example.hdmedi.ui.main.HomeActivity
import com.example.hdmedi.databinding.ActivityParentsSettingBinding
import com.example.hdmedi.model.SignUpRequestBody
import com.example.hdmedi.model.SignUpResponseBody
import com.example.hdmedi.service.RetrofitService
import com.example.hdmedi.service.RetrofitBuilder
import com.example.hdmedi.util.base.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParentsSettingActivity : BaseActivity<ActivityParentsSettingBinding>(R.layout.activity_parents_setting),
    PostDialogData {
    private val RetrofitService = RetrofitBuilder.retrofitInstance().create<RetrofitService>(com.example.hdmedi.service.RetrofitService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initGenderButton()
        initEditText()
        initBirthButton()
        initNextButton()
    }

    private fun initNextButton(){
        binding.nextButton.setOnClickListener{
            if(binding.nextButton.isActivated){
                val childrenName = binding.nameText.text.toString()
                val birthday = binding.birthButton.text.toString()
                Log.d("birth", birthday)
                //sharedPreference 이름,생일,성별 저장
                if(binding.maleButton.isSelected) {
                    preferences.setString("gender", "man")
                } else if (binding.femaleButton.isSelected) {
                    preferences.setString("gender", "woman")
                }

                preferences.apply {
                    setString("childrenName", childrenName)
                    setString("birthday",birthday)
                    setBoolean("isLogin", true)
                }
                val accessToken =  preferences.getString("accessToken", "")

                try{
                    RetrofitService.postSignUp(
                        "Bearer $accessToken",
                        SignUpRequestBody(
                            preferences.getString("userName",""),
                            preferences.getString("childrenName",""),
                            preferences.getString("birthday",""),
                            preferences.getString("gender",""), "naver")).
                    enqueue(object : Callback<SignUpResponseBody> {
                        override fun onResponse(call: Call<SignUpResponseBody>, response: Response<SignUpResponseBody>) {
                            if(response.isSuccessful) {
                                val accessToken = response.body()!!.data.accessToken
                                preferences.setString("accessToken", accessToken)

                                val intent = Intent(this@ParentsSettingActivity, HomeActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            }else {
                                Log.d("SignUpResponseBody Response : ", " fail 1 , ${response.toString()}")
                            }
                        }
                        override fun onFailure(call: Call<SignUpResponseBody>, t: Throwable) {
                            Log.d("SignUpResponseBody Response : ", " fail 2 , ${t.message.toString()}")
                        }
                    })
                } catch (e:Exception) {
                    Log.d("SignUpResponseBody response : ", " fail 3 , ${e.message}")
                }
            }
        }
    }

    private fun initBirthButton(){
        binding.birthButton.setOnClickListener {
            val dialog = BirthPickerDialog(this)
            dialog.initDialog()
        }
    }

    override fun postData(data: String) {
        binding.birthButton.apply {
            isSelected = true
            text = data
            checkNextButton()
        }
    }

    private fun checkNextButton(){
        if((binding.maleButton.isSelected || binding.femaleButton.isSelected)
            && binding.birthButton.isSelected
            && binding.nameText.text.isNotEmpty())
        {
            binding.nextButton.apply {
                isActivated = true
                setTextColor(Color.WHITE)
            }
        }else{
            binding.nextButton.apply {
                isActivated = false
                setTextColor(Color.BLACK)
            }
        }
    }

    private fun initEditText(){
        binding.nameText.setOnFocusChangeListener { view, b ->
            if(!b && binding.nameText.text.isNotEmpty()){
                view.isSelected = true
                checkNextButton()
            }else{
                view.isSelected = b
                checkNextButton()
            }
        }
    }

    private fun initGenderButton(){
        binding.maleButton.setOnClickListener{
            it.isSelected = true
            binding.femaleButton.isSelected = false
            checkNextButton()
        }

        binding.femaleButton.setOnClickListener{
            it.isSelected = true
            binding.maleButton.isSelected = false
            checkNextButton()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        binding.nameText.clearFocus()
        return super.dispatchTouchEvent(ev)
    }
}