package com.example.hdmedi.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hdmedi.R
import com.example.hdmedi.databinding.FragmentTestStartBinding
import com.example.hdmedi.sharedPreference.MyApplication

class TestStartFragment : Fragment() {
    private var _binding :FragmentTestStartBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTestStartBinding.inflate(inflater,container,false)
        val view = binding.root
        initTitleText()
        //시작하기 버튼 클릭
        binding.btnStart.setOnClickListener {
            val testFragment = TestFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameLayout,testFragment)
                addToBackStack(null)
                commit()
            }
        }
        return view
    }

    private fun initTitleText(){
        val greenSpan = ForegroundColorSpan(Color.parseColor("#2BAE76"))
        val name = MyApplication.preferences.getString("childrenName", "")
        binding.titleText.text = name + "님의 \nADHD 자가진단을 \n시작할까요?"
        val textData = binding.titleText.text.toString()
        binding.titleText.text = SpannableStringBuilder(textData).apply {
            setSpan(greenSpan, 0, name.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}