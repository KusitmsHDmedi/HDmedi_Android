package com.example.hdmedi.ui.test.result.sendResult

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityRequestResultParentBinding
import com.example.hdmedi.model.AuthCodeResponseBody
import com.example.hdmedi.service.RetrofitService
import com.example.hdmedi.service.RetrofitBuilder
import com.example.hdmedi.HDmediApplication
import com.example.hdmedi.ui.main.HomeActivity
import com.example.hdmedi.util.base.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestResultParentActivity : BaseActivity<ActivityRequestResultParentBinding>(R.layout.activity_request_result_parent),
    DialogListener {
    private val RetrofitService = RetrofitBuilder.retrofitInstance().create<RetrofitService>(com.example.hdmedi.service.RetrofitService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editText = arrayOf(binding.numberText, binding.detailText)
        initEditText(binding.numberText, 11)
        initDetailText()
        initSendButton()
        initBackButton()
        initExitButton()
        getAuthCode()
    }

    override fun onYesButtonClickListener() {
        checkPermission()
    }

    private fun initBackButton(){
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun initExitButton(){
        binding.exitButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(this)
            }
            finish()
        }
    }

    private fun initSendButton(){
        binding.sendButton.apply {
            setOnClickListener {
                if(isActivated){
                    val dialog = RequestDialog(this@RequestResultParentActivity)
                    dialog.setDialogListener(this@RequestResultParentActivity)
                    dialog.initDialog()
                }else{
                    Toast.makeText(this@RequestResultParentActivity, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initCodeText(code: String){
        binding.codeText.text = code
    }

    private fun initEditText(e: EditText, length: Int){
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
        val smsManager = SmsManager.getDefault()
        val parts = smsManager.divideMessage(message)

        smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null)
        Intent(this, SendResultActivity::class.java).apply {
            startActivity(this)
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun activateSendButton(){
        binding.sendButton.apply {
            if(binding.numberText.isActivated && binding.detailText.isActivated) {
                isActivated = true
                setTextColor(Color.WHITE)
            }else{
                isActivated = false
                setTextColor(R.color.gray700)
            }
        }
    }

    private fun checkPermission(){
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
        if(permission==PackageManager.PERMISSION_GRANTED){
            sendMessage(binding.numberText.text.toString(), setMessageText())
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS),99)
        }
    }

    private fun getAuthCode(){
        RetrofitService.getAuthCode("Bearer "+ HDmediApplication.preferences.getString("accessToken", "")).
        enqueue(object: Callback<AuthCodeResponseBody> {
            override fun onResponse(
                call: Call<AuthCodeResponseBody>,
                response: Response<AuthCodeResponseBody>
            ) {
                if(response.isSuccessful){
                    initCodeText(response.body()!!.data.authCode)
                }
            }

            override fun onFailure(call: Call<AuthCodeResponseBody>, t: Throwable) { }
        })
    }

    private fun setMessageText(): String {
        val name = HDmediApplication.preferences.getString("childrenName", "")
        return "안녕하세요 아이약 서비스입니다.\n" + name + "" +
                "의 학부모님께서 아이의 ADHD 자가진단 검 사를 요청하였습니다. 아이와 학부모를 위해 평소 선 생님이 바라본 아이의 모습을 바탕으로 검사해주면 감사하겠습니다.\n\n" +
                name + "의 학부모 요청사항\n" +
                "[" + binding.detailText.text.toString() + "]\n\n" +
                "아이의 자가진단에 참여하시려면 하단의 다운로드 링 크를 통해 어플을 다운로드 받은 후 아이 코드를 입력 해주세요!\n" +
                "다운로드 링크 : https://play.google.com/store/apps/details?id=kr.co.hdmedi.boedoc&hl=ko-KR\n" +
                "아이코드 : " + binding.codeText.text.toString()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            99 -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    sendMessage(binding.numberText.text.toString(), setMessageText())
                }else{
                    Toast.makeText(this, "결과를 전송하려면 권한이 필요합니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}