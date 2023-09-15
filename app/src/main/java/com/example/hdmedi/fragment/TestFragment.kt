package com.example.hdmedi.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.hdmedi.R
import com.example.hdmedi.databinding.FragmentTestBinding
import com.example.hdmedi.resultViewModel
import kotlin.math.log


class TestFragment : Fragment() {

    private lateinit var viewModel : resultViewModel

    private var isCheck = false
    private var _binding : FragmentTestBinding?= null
    private val binding get() = _binding!!

    private var questionScore = 0

    private var answer1 = ""
    private var totalScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTestBinding.inflate(inflater,container,false)
        val view = binding.root





        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(resultViewModel::class.java)
        //나가기 클릭
        binding.textExit.setOnClickListener {

            val testStartFragment = TestStartFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameLayout,testStartFragment)
                commit()
            }

        }

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


                viewModel.addScore(questionScore)

                viewModel.addArray(answer1)


                Log.d("viewmodel1, ", "${viewModel.TotalScore.value},${viewModel.answerArrayList.value}")

                totalScore += questionScore


                val bundle = Bundle()
                bundle.putInt("score1", totalScore)
                bundle.putString("answer1", answer1)



                Log.d("answer1" , answer1.toString())
//                setFragmentResult("totalScore0", bundleOf("bundleKey" to totalScore,"bundleKey1" to answer1))
//                setFragmentResult("answer1", bundleOf("bundleKey" to answer1 ))
                val test2To18Fragment = Test2To18Fragment()
                test2To18Fragment.arguments = bundle


                fragmentManager?.beginTransaction()?.apply {
                    replace(R.id.frameLayout,test2To18Fragment)
                    addToBackStack(null)
                    commit()
                }

            }

        }
    }


}