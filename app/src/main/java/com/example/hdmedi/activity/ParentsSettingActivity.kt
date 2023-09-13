package com.example.hdmedi.activity

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityParentsSettingBinding

class ParentsSettingActivity : BindingActivity<ActivityParentsSettingBinding>(R.layout.activity_parents_setting), PostDialogData {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initGenderButton()
        initEditText()
        initBirthButton()
        initNextButton()
    }

    private fun initNextButton(){
        if(binding.nextButton.isActivated){
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