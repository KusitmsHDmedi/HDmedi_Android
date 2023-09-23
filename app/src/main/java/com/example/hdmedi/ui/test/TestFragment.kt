package com.example.hdmedi.ui.test

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.hdmedi.R
import com.example.hdmedi.databinding.FragmentTestBinding
import com.example.hdmedi.util.ResultViewModel


class TestFragment : Fragment(){
    private lateinit var callback: OnBackPressedCallback
    private var isCheck = false
    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!
    private var questionScore = 0
    private var answer1 = ""
    private  val viewModel: ResultViewModel by activityViewModels()
    private var totalScore1 = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initExitButton()
        //전혀 그렇지 않다 클릭
        binding.answer0.setOnClickListener {
            //체크 아이콘 변경
            binding.checkAnswer0.setImageResource(R.drawable.check)
            binding.checkAnswer1.setImageResource(R.drawable.uncheck)
            binding.checkAnswer2.setImageResource(R.drawable.uncheck)
            binding.checkAnswer3.setImageResource(R.drawable.uncheck)

            //배경색 변경
            binding.answer0Color.setBackgroundColor(Color.parseColor("#c4eadc"))
            binding.answer1Color.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.answer2Color.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.answer3Color.setBackgroundColor(Color.parseColor("#ffffff"))

            questionScore = 0
            answer1 = "전혀 그렇지 않다"
            isCheck = true

            if (isCheck == true) {

                binding.btnNextColor.setTextColor(Color.parseColor("#FFFFFF"))
                binding.nextColor.setBackgroundColor(Color.parseColor("#00C67B"))

            }
        }

        //가끔 그렇다 클릭
        binding.answer1.setOnClickListener {

            //체크 아이콘 변경
            binding.checkAnswer1.setImageResource(R.drawable.check)
            binding.checkAnswer0.setImageResource(R.drawable.uncheck)
            binding.checkAnswer2.setImageResource(R.drawable.uncheck)
            binding.checkAnswer3.setImageResource(R.drawable.uncheck)

            //배경색 변경
            binding.answer1Color.setBackgroundColor(Color.parseColor("#c4eadc"))
            binding.answer0Color.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.answer2Color.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.answer3Color.setBackgroundColor(Color.parseColor("#ffffff"))

            questionScore = 1
            answer1 = "가끔 그렇다"
            isCheck = true

            if (isCheck == true) {

                binding.btnNextColor.setTextColor(Color.parseColor("#FFFFFF"))
                binding.nextColor.setBackgroundColor(Color.parseColor("#00C67B"))

            }
        }

        //자주 그렇다 클릭
        binding.answer2.setOnClickListener {
            //체크 아이콘 변경
            binding.checkAnswer2.setImageResource(R.drawable.check)
            binding.checkAnswer1.setImageResource(R.drawable.uncheck)
            binding.checkAnswer0.setImageResource(R.drawable.uncheck)
            binding.checkAnswer3.setImageResource(R.drawable.uncheck)

            //배경색 변경
            binding.answer2Color.setBackgroundColor(Color.parseColor("#c4eadc"))
            binding.answer1Color.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.answer0Color.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.answer3Color.setBackgroundColor(Color.parseColor("#ffffff"))

            questionScore = 2
            answer1 = "자주 그렇다"
            isCheck = true

            if (isCheck == true) {

                binding.btnNextColor.setTextColor(Color.parseColor("#FFFFFF"))
                binding.nextColor.setBackgroundColor(Color.parseColor("#00C67B"))

            }
        }

        //매우 자주 그렇다 클릭
        binding.answer3.setOnClickListener {

            //체크 아이콘 변경
            binding.checkAnswer3.setImageResource(R.drawable.check)
            binding.checkAnswer1.setImageResource(R.drawable.uncheck)
            binding.checkAnswer2.setImageResource(R.drawable.uncheck)
            binding.checkAnswer0.setImageResource(R.drawable.uncheck)

            //배경색 변경
            binding.answer3Color.setBackgroundColor(Color.parseColor("#c4eadc"))
            binding.answer1Color.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.answer2Color.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.answer0Color.setBackgroundColor(Color.parseColor("#ffffff"))

            questionScore = 3
            answer1 = "매우 자주 그렇다"
            isCheck = true

            if (isCheck == true) {
                binding.btnNextColor.setTextColor(Color.parseColor("#FFFFFF"))
                binding.nextColor.setBackgroundColor(Color.parseColor("#00C67B"))

            }
        }

        //다음 클릭
        binding.btnNext.setOnClickListener {
            if (isCheck == true) {
                viewModel.addviewModelscore(questionScore)
                viewModel.addviewModelScoreList(questionScore)
                viewModel.addviewModelAnswerList(answer1)

                Log.d("viewModel", "${viewModel.viewModelAnswerList} " +
                        " \n ${viewModel.viewModelscore}")
                val test2To18Fragment = Test2To18Fragment()

                fragmentManager?.beginTransaction()?.apply {
                    replace(R.id.frameLayout, test2To18Fragment)
                    commit()
                }
            }
        }
    }

    private fun initExitButton(){
        binding.textExit.setOnClickListener {
            val dialog = ExitDialog(context as AppCompatActivity)
            dialog.initDialog()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val dialog = ExitDialog(context as AppCompatActivity)
                dialog.initDialog()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}