package com.example.hdmedi.ui.test

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.hdmedi.R
import com.example.hdmedi.databinding.FragmentTestStartBinding
import com.example.hdmedi.HDmediApplication

class TestStartFragment : Fragment() {
    private lateinit var callback: OnBackPressedCallback
    private var _binding :FragmentTestStartBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestStartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStartButton()
        initTitleText()
        initGenderImage(HDmediApplication.preferences.getString("gender", ""))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    private fun initStartButton(){
        binding.btnStart.setOnClickListener {
            val testFragment = TestFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameLayout,testFragment)
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun initTitleText(){
        val greenSpan = ForegroundColorSpan(Color.parseColor("#2BAE76"))
        val name = HDmediApplication.preferences.getString("childrenName", "")
        binding.titleText.text = name + "님의 \nADHD 자가진단을 \n시작할까요?"
        val textData = binding.titleText.text.toString()
        binding.titleText.text = SpannableStringBuilder(textData).apply {
            setSpan(greenSpan, 0, name.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    private fun initGenderImage(gender: String){
        if(gender=="woman"){
            binding.image.setBackgroundResource(R.drawable.main_girl)
        }else if(gender=="man"){
            binding.image.setBackgroundResource(R.drawable.main_boy)
        }
    }
}