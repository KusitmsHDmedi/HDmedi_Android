package com.example.hdmedi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hdmedi.R
import com.example.hdmedi.databinding.FragmentResult1Binding
import com.example.hdmedi.databinding.FragmentResult2Binding


class Result2Fragment : Fragment() {

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


}