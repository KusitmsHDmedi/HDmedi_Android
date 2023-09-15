package com.example.hdmedi.activity

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityRequestResultParentBinding

class RequestResultParentActivity : BaseActivity<ActivityRequestResultParentBinding>(R.layout.activity_request_result_parent), DialogListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editText = arrayOf(binding.numberText, binding.codeText, binding.detailText)
        initEditTEXT(binding.numberText, 11)
        initEditTEXT(binding.codeText, 5)
        initDetailText()
        initSendButton()
    }

    override fun onYesButtonClickListener() {
        checkPermission()
    }

    private fun initSendButton(){
        binding.sendButton.apply {
            setOnClickListener {
                if(isActivated){
                    val dialog = RequestDialog(this@RequestResultParentActivity)
                    dialog.setDialogListener(this@RequestResultParentActivity)
                    dialog.initDialog()
                }
            }
        }
    }

    private fun initEditTEXT(e: EditText, length: Int){
        e.setOnFocusChangeListener { view, b ->
            if(!b && e.text.isNotEmpty()){
                view.isSelected = true
                if(e.text.length==length){
                    e.isActivated = true
                    activateSendButton()
                }else{
                    e.isActivated = false
                    activateSendButton()
                }
            }else{
                view.isSelected = b
            }
        }
    }

    private fun initDetailText(){
        binding.detailText.setOnFocusChangeListener { view, b ->
            if(!b && binding.detailText.text.isNotEmpty()){
                view.isSelected = true
                binding.detailText.isActivated = true
                activateSendButton()
            }else{
                binding.detailText.isActivated = false
                activateSendButton()
                view.isSelected = b
            }
        }
    }

    private fun sendMessage(phoneNumber: String, message: String) {
        if(binding.sendButton.isActivated){
            val smsManager = SmsManager.getDefault()
            val sentIntent = Intent("SMS_SENT")
            val deliveredIntent = Intent("SMS_DELIVERED")

            val sentPI = PendingIntent.getBroadcast(this, 0, sentIntent, PendingIntent.FLAG_IMMUTABLE)
            val deliveredPI = PendingIntent.getBroadcast(this, 0, deliveredIntent,
                PendingIntent.FLAG_IMMUTABLE)

            smsManager.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI)
        }else{
            Toast.makeText(this, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    private fun activateSendButton(){
        binding.sendButton.apply {
            if(binding.numberText.isActivated && binding.detailText.isActivated && binding.codeText.isActivated) {
                isActivated = true
                setTextColor(Color.WHITE)
            }else{
                isActivated = false
                setTextColor(Color.BLACK)
            }
        }
    }

    private fun checkPermission(){
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
        if(permission==PackageManager.PERMISSION_GRANTED){
            sendMessage(binding.numberText.text.toString(), binding.detailText.text.toString())
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS),99)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            99 -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    sendMessage(binding.numberText.text.toString(), binding.detailText.text.toString())
                }else{
                    Toast.makeText(this, "결과를 전송하려면 권한이 필요합니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}