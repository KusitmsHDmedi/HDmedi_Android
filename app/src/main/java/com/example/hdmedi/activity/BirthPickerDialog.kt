package com.example.hdmedi.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.hdmedi.databinding.DialogBirthPickerBinding
import sh.tyy.wheelpicker.DatePickerView
import java.util.Calendar
import java.util.Date

interface PostDialogData {
    fun postData(data: String)
}

class BirthPickerDialog(private val context: AppCompatActivity) {
    private val binding = DialogBirthPickerBinding.inflate(context.layoutInflater)
    private val dialog = Dialog(context)
    private val calendar = Calendar.getInstance()

    fun initDialog(){
        setDialog()
        dialog.show()
        initDatePicker()
        initSetButton()
    }

    private fun initSetButton(){
        binding.setButton.setOnClickListener {
            val listener = context as PostDialogData
            listener.postData(binding.dateText.text.toString())
            dialog.dismiss()
        }
    }

    private fun initDatePicker(){
        val min = Calendar.getInstance()

        binding.datePicker.apply {
            isHapticFeedbackEnabled = false
            min.set(2003, 8, 14)
            minDate = min.time
            maxDate = Date()
            setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            setWheelListener(object : DatePickerView.Listener {
                override fun didSelectData(year: Int, month: Int, dayOfMonth: Int) {

//                    binding.dateText.text = "${year}년 ${month+1}월 ${day}일"
                    var realMonth = "${month+1}"
                    var realDayOfMonth = "${dayOfMonth}"

                    if(month+1<10) {
                        realMonth ="0${month+1}"
                    }
                    if (dayOfMonth<10) {
                        realDayOfMonth = "0$dayOfMonth"
                    }

                    val selectDate = "${year}-${realMonth}-${realDayOfMonth}"
                    binding.dateText.setText(selectDate)
                }
            })
        }
    }

    private fun setDialog(){
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(binding.root)
            window?.apply {
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                setGravity(Gravity.BOTTOM)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }
}