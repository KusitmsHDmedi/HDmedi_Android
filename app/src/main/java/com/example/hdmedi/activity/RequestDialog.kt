package com.example.hdmedi.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.hdmedi.databinding.DialogRequestBinding
import com.example.hdmedi.sharedPreference.MyApplication

interface DialogListener {
    fun onYesButtonClickListener()
}

class RequestDialog(private val context: AppCompatActivity) {
    private val binding = DialogRequestBinding.inflate(context.layoutInflater)
    private val dialog = Dialog(context)
    private var dialogListener: DialogListener? = null

    fun setDialogListener(listener: DialogListener){
        dialogListener = listener
    }

    fun initDialog(){
        initTitleText()
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(binding.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        binding.noButton.setOnClickListener {
            dialog.dismiss()
        }

        binding.yesButton.setOnClickListener {
            dialogListener?.onYesButtonClickListener()
        }
        dialog.show()
    }

    private fun initTitleText(){
        val greenSpan = ForegroundColorSpan(Color.parseColor("#2BAE76"))
        val name = MyApplication.preferences.getString("childrenName", "")
        binding.titleText.text = name + "의 자가진단을\n 선생님께 요청시겠습니까?"
        val textData = binding.titleText.text.toString()
        binding.titleText.text = SpannableStringBuilder(textData).apply {
            setSpan(greenSpan, 0, name.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}