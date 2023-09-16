package com.example.hdmedi.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.hdmedi.R
import com.example.hdmedi.activity.RequestResultParentActivity
import com.example.hdmedi.databinding.FragmentResult1Binding
import com.example.hdmedi.databinding.FragmentResult2Binding
import com.example.hdmedi.resultViewModel


class Result2Fragment : Fragment() {

    private  val viewModel: resultViewModel by activityViewModels()


    private var _binding: FragmentResult2Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentResult2Binding.inflate(inflater,container,false)
        val view = binding.root



        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val score = viewModel.viewModelscore

        binding.finalScore.setText(score.toString())


        if(score >=19) {
            binding.textResult.setText("주의력결핍 및 과잉행동 장애일 가능성이 있습니다. 주의력 결핍 과잉장애 문제는 성장하면서 자연스럽게 좋아질 수도 있으나 많은 경우 학습이나 또래 혹은 형제들과의 관계에서 다양한 문제가 발생할 소지가 있으므로 정확한 진단을 위해 전문가와 상담해 보는 것이 좋습니다.")
        } else {
            binding.textResult.setText("크게 문제될 것이 없습니다. 그러나 아동은 성장기에 급속한 변화를 보일 수 있으므로 추후에도 자녀의 성장에 많은 관심 부탁드립니다. ADHD에 관한 더 자세한 내용을 원하시는 분은 www.adhd.or.kr를 참조하세요!")
        }


        //자가진단 요청하기
        binding.btnNext.setOnClickListener {
            //request result parent 연결

            val intent = Intent(requireContext(), RequestResultParentActivity::class.java)
            startActivity(intent)
        }
    }

}