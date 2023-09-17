package com.example.hdmedi.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityParentsSettingBinding
import com.example.hdmedi.model.SignUpRequestBody
import com.example.hdmedi.model.SignUpResponseBody
import com.example.hdmedi.retrofit.APIS
import com.example.hdmedi.retrofit.RetrofitInstance
import com.example.hdmedi.sharedPreference.MyApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParentsSettingActivity : BaseActivity<ActivityParentsSettingBinding>(R.layout.activity_parents_setting), PostDialogData {
    private val APIS = RetrofitInstance.retrofitInstance().create<APIS>(com.example.hdmedi.retrofit.APIS::class.java)

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
                    MyApplication.preferences.setString("gender", "man")
                } else if (binding.femaleButton.isSelected) {
                    MyApplication.preferences.setString("gender", "woman")
                }

                MyApplication.preferences.setString("childrenName", childrenName)
                MyApplication.preferences.setString("birthday",birthday)
                MyApplication.preferences.setBoolean("isLogin", true)

                val accessToken =  MyApplication.preferences.getString("accessToken", "")
                Log.d("accessToken2",accessToken)
                try{
                    APIS.postSignUp(
                        "Bearer $accessToken",
                        SignUpRequestBody(
                            MyApplication.preferences.getString("userName",""),
                            MyApplication.preferences.getString("childrenName",""),
                            MyApplication.preferences.getString("birthday",""),
                            MyApplication.preferences.getString("gender",""), "naver")).
                    enqueue(object : Callback<SignUpResponseBody> {

                        override fun onResponse(call: Call<SignUpResponseBody>, response: Response<SignUpResponseBody>) {
                            if (response.isSuccessful) {
                                val accessToken = response.body()!!.data.accessToken
                                MyApplication.preferences.setString("accessToken", accessToken)

                                val intent = Intent(baseContext, HomeActivity::class.java)
                                startActivity(intent)
                            } else {
                                Log.d("SignUpResponseBody Response : ", " fail 1 , ${response.message()}")
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