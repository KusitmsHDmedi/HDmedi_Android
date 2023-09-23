package com.example.hdmedi.ui.onboarding.teacher

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityTeacherSettingBinding
import com.example.hdmedi.model.GuestSignInResponseBody
import com.example.hdmedi.service.RetrofitService
import com.example.hdmedi.service.RetrofitBuilder
import com.example.hdmedi.HDmediApplication
import com.example.hdmedi.util.base.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeacherSettingActivity : BaseActivity<ActivityTeacherSettingBinding>(R.layout.activity_teacher_setting) {
    private val RetrofitService = RetrofitBuilder.retrofitInstance().create<RetrofitService>(com.example.hdmedi.service.RetrofitService::class.java)

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
        binding.codeText.apply {
            setOnFocusChangeListener { view, b ->
                if(!b && binding.codeText.text.isNotEmpty()){
                    view.isSelected = true
//                    binding.nextButton.isActivated = true
//                    binding.nextButton.setTextColor(Color.WHITE)
                }else{
                    view.isSelected = b
//                    binding.nextButton.isActivated = false
//                    binding.nextButton.setTextColor(getColor(R.color.gray700))
                }
            }

            addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val text = p0.toString()
                    if(text.length==6) {
                        binding.nextButton.isActivated = true
                        binding.nextButton.setTextColor(getColor(R.color.white))
                    }else{
                        binding.nextButton.isActivated = false
                        binding.nextButton.setTextColor(getColor(R.color.gray700))
                    }
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
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
        RetrofitService.postGuestSignIn("Bearer " + binding.codeText.text.toString()).enqueue(object: Callback<GuestSignInResponseBody>{
            override fun onResponse(
                call: Call<GuestSignInResponseBody>,
                response: Response<GuestSignInResponseBody>
            ) {
                if(response.isSuccessful){
                    val data = response.body()!!.data
                    HDmediApplication.preferences.setString("accessToken", data.accessToken)
                    HDmediApplication.preferences.setString("childrenName", data.childName)
                    HDmediApplication.preferences.setString("birthday", data.birthday)
                    HDmediApplication.preferences.setString("userName", data.parentsName)
                    HDmediApplication.preferences.setString("gender", data.gender.toLowerCase())
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