package com.example.hdmedi.ui.test.result

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hdmedi.R
import com.example.hdmedi.ui.test.result.sendResult.SendResultActivity
import com.example.hdmedi.databinding.FragmentResult1Binding
import com.example.hdmedi.model.resultData
import com.example.hdmedi.HDmediApplication
import com.example.hdmedi.ui.test.result.sendResult.DialogListener
import com.example.hdmedi.ui.test.result.sendResult.RequestDialog
import com.example.hdmedi.util.ResultAdapter
import com.example.hdmedi.util.ResultViewModel

class Result1Fragment : Fragment(), DialogListener {
    private  val viewModel: ResultViewModel by activityViewModels()
    private lateinit var resultAdapter: ResultAdapter
    private var _binding: FragmentResult1Binding? = null
    private val binding get() = _binding!!
    private var resultArray = ArrayList<resultData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResult1Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initColor()
        initNextButton()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val question = resources.getStringArray(R.array.question)
        val answerArrayList = viewModel.viewModelAnswerList
        for(i in 0 until 18){
            resultArray.add(resultData(question[i], answerArrayList[i]))
        }
        resultAdapter = ResultAdapter(resultArray)
        binding.rvResult.adapter = resultAdapter
        binding.rvResult.layoutManager = LinearLayoutManager(context)
    }

    //교사인지 학부모인지 판단해서 색 결정
    private fun initColor(){
        when(HDmediApplication.preferences.getString("who","")){
            "parent" -> {
                binding.answerBack.setBackgroundColor(Color.parseColor("#00C67B"))
                binding.answerText.setTextColor(Color.parseColor("#FFFFFF"))
            }
            "teacher" -> {
                binding.btnNext.text = "부모님께 전달하기"
                binding.answerBack.setBackgroundColor(Color.parseColor("#FFE459"))
                binding.answerText.setTextColor(Color.parseColor("#000000"))
            }
        }
    }

    private fun initNextButton(){
        binding.btnNext.setOnClickListener {
            when(HDmediApplication.preferences.getString("who","")){
                "parent" -> {
                    val result2Fragment = Result2Fragment()
                    fragmentManager?.beginTransaction()?.apply {
                        replace(R.id.frameLayout, result2Fragment)
                        addToBackStack(null)
                        commit()
                    }
                }
                "teacher" -> {
                    val dialog = RequestDialog(activity as AppCompatActivity)
                    dialog.setDialogListener(this@Result1Fragment)
                    dialog.initDialog()
                }
            }
        }
    }

    override fun onYesButtonClickListener() {
        Intent(context, SendResultActivity::class.java).apply {
            startActivity(this)
            activity?.finish()
        }
    }
}