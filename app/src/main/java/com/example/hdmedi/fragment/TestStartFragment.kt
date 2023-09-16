package com.example.hdmedi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.hdmedi.R
import com.example.hdmedi.databinding.FragmentTestStartBinding
import kotlin.concurrent.fixedRateTimer


class TestStartFragment : Fragment() {


    private var _binding :FragmentTestStartBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTestStartBinding.inflate(inflater,container,false)
        val view = binding.root



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


}