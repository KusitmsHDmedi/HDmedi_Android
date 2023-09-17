package com.example.hdmedi.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityTeacherSettingBinding
import com.example.hdmedi.model.GuestSignInResponseBody
import com.example.hdmedi.retrofit.APIS
import com.example.hdmedi.retrofit.RetrofitInstance
import com.example.hdmedi.sharedPreference.MyApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeacherSettingActivity : BaseActivity<ActivityTeacherSettingBinding>(R.layout.activity_teacher_setting) {
    private val APIS = RetrofitInstance.retrofitInstance().create<APIS>(com.example.hdmedi.retrofit.APIS::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTitleText()
        initEditText()
        initBackButton()
        initNextButton()
    }

    private fun initBackButton(){
       binding.backButton.setOnClickListener {
           finish()
       }
    }

    private fun initEditText(){
        binding.codeText.setOnFocusChangeListener { view, b ->
            if(!b && binding.codeText.text.isNotEmpty()){
                view.isSelected = true
                binding.nextButton.isActivated = true
                binding.nextButton.setTextColor(Color.WHITE)
            }else{
                view.isSelected = b
                binding.nextButton.isActivated = false
                binding.nextButton.setTextColor(getColor(R.color.gray700))
            }
        }
    }

    private fun initTitleText(){
        val greenSpan = ForegroundColorSpan(Color.parseColor("#2BAE76"))
        val sizeSpan = RelativeSizeSpan(1.5f)
        val boldSpan = StyleSpan(Typeface.BOLD)
        val textData = binding.titleText.text.toString()

        binding.titleText.text = SpannableStringBuilder(textData).apply {
            setSpan(sizeSpan, 13, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(boldSpan, 13, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(greenSpan, 13, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    private fun initNextButton(){
        binding.nextButton.setOnClickListener {
            if(it.isActivated){
                initLogin()
            }
        }
    }

    private fun initLogin(){
        APIS.postGuestSignIn("Bearer " + binding.codeText.text.toString()).enqueue(object: Callback<GuestSignInResponseBody>{
            override fun onResponse(
                call: Call<GuestSignInResponseBody>,
                response: Response<GuestSignInResponseBody>
            ) {
                if(response.isSuccessful){
                    val data = response.body()!!.data
                    MyApplication.preferences.setString("accessToken", data.accessToken)
                    MyApplication.preferences.setString("childrenName", data.childName)
                    MyApplication.preferences.setString("birthday", data.birthday)
                    MyApplication.preferences.setString("userName", data.parentsName)
                    MyApplication.preferences.setString("gender", data.gender)
                    Log.d("testtt", data.gender)
                    Intent(this@TeacherSettingActivity, CheckInfoActivity::class.java).apply {
                        startActivity(this)
                        finish()
                    }
                }else{
                    Toast.makeText(this@TeacherSettingActivity, "올바른 코드를 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GuestSignInResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        binding.codeText.clearFocus()
        return super.dispatchTouchEvent(ev)
    }
}