package com.example.hdmedi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.hdmedi.R
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

        //자가진단 요청하기
        binding.btnNext.setOnClickListener {
            //request result parent 연결
        }
    }

}