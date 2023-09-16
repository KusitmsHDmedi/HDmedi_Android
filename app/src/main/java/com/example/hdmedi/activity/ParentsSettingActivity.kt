package com.example.hdmedi.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityParentsSettingBinding
import com.example.hdmedi.sharedPreference.MyApplication

class ParentsSettingActivity : BaseActivity<ActivityParentsSettingBinding>(R.layout.activity_parents_setting), PostDialogData {
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

            val name = binding.nameText.text.toString()

            val birth = binding.birthButton.text.toString()
            //sharedPreference 이름,생일,성별 저장

            if(binding.maleButton.isSelected) {
                MyApplication.preferences.setString("gender", "남자아이")
            } else if (binding.femaleButton.isSelected) {
                MyApplication.preferences.setString("gender", "여자아이")

            }
            MyApplication.preferences.setString("name", name)

            MyApplication.preferences.setString("birth",birth )
                val intent = Intent(this, HomeActivity::class.java)

                startActivity(intent)
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