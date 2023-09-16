package com.example.hdmedi.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.hdmedi.databinding.DialogRequestBinding

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
}