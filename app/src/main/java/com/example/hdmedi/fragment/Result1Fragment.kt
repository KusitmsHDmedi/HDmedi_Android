package com.example.hdmedi.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hdmedi.R
import com.example.hdmedi.ResultAdapter
import com.example.hdmedi.databinding.FragmentResult1Binding
import com.example.hdmedi.databinding.FragmentTest2To18Binding
import com.example.hdmedi.model.resultData
import com.example.hdmedi.resultViewModel


class Result1Fragment : Fragment() {

    private  val viewModel: resultViewModel by activityViewModels()

    private lateinit var resultAdapter: ResultAdapter
    private var _binding: FragmentResult1Binding? = null
    private val binding get() = _binding!!


    private var resultArray = ArrayList<resultData>()


    private var answerArrayList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentResult1Binding.inflate(inflater,container,false)
        val view = binding.root




        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        answerArrayList = viewModel.viewModelAnswerList



        //데이터 넣기
        resultArray.add(resultData("1. 우리 아이가 세세한 부분에서 \n꼼꼼하게 신경쓰는 것을 \n어려워 하나요?","${answerArrayList.get(0)}"))
        resultArray.add(resultData("2. 우리 아이가 손발을 가만히 \n" +
                "두지 못하거나 의자에 앉아서도 몸을 꼼지락 거리나요?","${answerArrayList.get(1)}"))
        resultArray.add(resultData( "3. 우리 아이가 놀이를 할 때 \n" +
                "지속적으로 집중하는 것에\n" +
                "어려워 하나요?","${answerArrayList.get(2)}"))
        resultArray.add(resultData("4. 우리 아이가 자리에 앉아 있어야 하는 교실이나 다른 상황에서 \n" +
                "앉아 있지를 못하나요?","${answerArrayList.get(3)}"))
        resultArray.add(resultData("5. 우리 아이가 다른 사람과 \n" +
                "마주보고 이야기할 때 경청하지 않는 것처럼 보이나요?","${answerArrayList.get(4)}"))
        resultArray.add(resultData("6. 우리 아이가 그렇게 행동하면\n" +
                "안 되는 상황에서 지나치게 뛰어다니거나 통제가 되지 않나요?","${answerArrayList.get(5)}"))
        resultArray.add(resultData("7. 우리 아이가 누군가의 말을 \n" +
                "잘 따르지 않고, 일을 제때\n" +
                "끝내지 못하나요?","${answerArrayList.get(6)}"))
        resultArray.add(resultData("8. 우리 아이가 여가 활동이나 \n" +
                "재미있는 활동에 조용히 \n" +
                "참여하기 어려워하나요?","${answerArrayList.get(7)}"))
        resultArray.add(resultData("9. 우리 아이가 학교 공부나 숙제를 체계적으로 하지 못하나요?","${answerArrayList.get(8)}"))
        resultArray.add(resultData("10. 우리 아이가 항상 끊임없이 \n" +
                "움직이거나 마치 모터가 달려서 움직이는 것처럼 행동하나요?","${answerArrayList.get(9)}"))
        resultArray.add(resultData("11. 우리 아이가 지속적인 노력이 \n" +
                "필요한 학교 공부나 숙제를 \n" +
                "하지 않으려 하나요?","${answerArrayList.get(10)}"))
        resultArray.add(resultData("12. 우리 아이가 지나치게 \n" +
                "말을 많이 하는 편인가요?","${answerArrayList.get(11)}"))
        resultArray.add(resultData("13. 우리 아이가 학교 공부나 숙제를 할 때 필요한 물건들을 \n" +
                "잘 잃어버리나요?","${answerArrayList.get(12)}"))
        resultArray.add(resultData("14. 우리 아이가 질문이 다 끝나기도 전에 성급하게 대답하나요?","${answerArrayList.get(13)}"))
        resultArray.add(resultData("15. 우리 아이가 쉽게 산만해 지나요?","${answerArrayList.get(14)}"))
        resultArray.add(resultData("16. 우리 아이가 차례를 기다리는데 어려움을 많이 느끼나요?","${answerArrayList.get(15)}"))
        resultArray.add(resultData("17. 우리 아이가 일상적으로 하는\n" +
                "일을 잘 잊어버리나요?","${answerArrayList.get(16)}"))
        resultArray.add(resultData("18. 우리 아이가 다른 사람을 방해하거나 간섭하나요?","${answerArrayList.get(17)}"))
        resultAdapter = ResultAdapter(resultArray)

        binding.rvResult.adapter = resultAdapter
        binding.rvResult.layoutManager = LinearLayoutManager(context)

        binding.btnNext.setOnClickListener {
            val result2Fragment = Result2Fragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameLayout, result2Fragment)
                addToBackStack(null)
                commit()
            }
        }
    }


}