package com.example.hdmedi.ui.test.result

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.hdmedi.ui.test.result.sendResult.RequestResultParentActivity
import com.example.hdmedi.databinding.FragmentResult2Binding
import com.example.hdmedi.HDmediApplication
import com.example.hdmedi.util.ResultViewModel


class Result2Fragment : Fragment() {
    private  val viewModel: ResultViewModel by activityViewModels()
    private var _binding: FragmentResult2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentResult2Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTitleText()
        initExitButton()
        initNextButton()
        initBackButton()

        val score = viewModel.viewModelScoreList.sum()
        Log.d("score",score.toString())
        binding.finalScore.text = score.toString()

        if(score >=19) {
            binding.textResult.text = "주의력결핍 및 과잉행동 장애일 가능성이 있습니다. 주의력 결핍 과잉장애 문제는 성장하면서 자연스럽게 좋아질 수도 있으나 많은 경우 학습이나 또래 혹은 형제들과의 관계에서 다양한 문제가 발생할 소지가 있으므로 정확한 진단을 위해 전문가와 상담해 보는 것이 좋습니다."
            binding.condition.text = "19점 이상일 경우"
        } else {
            binding.textResult.text = "19점 미만인 경우 주의력결핍 및 과잉행동 장애가 아닐 가능성이 있습니다. 그러나 아동은 성장기에 급속한 변화를 보일 수 있으므로 추후에도 자녀의 성장에 많은 관심을 가지고 지켜봐주시기를 부탁드립니다."
            binding.condition.text = "19점 미만일 경우"
        }
    }

    private fun initNextButton(){
        //자가진단 요청하기
        binding.btnNext.setOnClickListener {
            val intent = Intent(requireContext(), RequestResultParentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initBackButton(){
        binding.backButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun initExitButton(){
        binding.exitButton.setOnClickListener {
            activity?.finish()
        }
    }

    private fun initTitleText(){
        val greenSpan = ForegroundColorSpan(Color.parseColor("#2BAE76"))
        val name = HDmediApplication.preferences.getString("childrenName", "")
        binding.titleText.text= name + "의 \nADHD 자가진단 점수"
        val textData = binding.titleText.text.toString()
        binding.titleText.text = SpannableStringBuilder(textData).apply {
            setSpan(greenSpan, 0, name.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}