package com.example.hdmedi.ui.test

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.hdmedi.R
import com.example.hdmedi.databinding.DialogExitBinding

class ExitDialog(private val context: AppCompatActivity){
    private val binding = DialogExitBinding.inflate(context.layoutInflater)
    private val dialog = Dialog(context)

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
            context.supportFragmentManager.beginTransaction().apply {
                clearBackStack(context.supportFragmentManager)
                replace(R.id.frameLayout, TestStartFragment(), "YourFragmentTag")
                commit()
            }
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun clearBackStack(fragmentManager: FragmentManager) {
        val backStackCount = fragmentManager.backStackEntryCount
        for (i in 0 until backStackCount) {
            fragmentManager.popBackStackImmediate()
        }
    }
}