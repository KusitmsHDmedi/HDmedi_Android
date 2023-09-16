package com.example.hdmedi.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import com.example.hdmedi.R
import com.example.hdmedi.activity.ExitDialog
import com.example.hdmedi.databinding.FragmentTest2To18Binding
import com.example.hdmedi.resultViewModel


class Test2To18Fragment : Fragment() {

    private  val viewModel: resultViewModel by activityViewModels()


    private var isCheck = false
    private var _binding: FragmentTest2To18Binding? = null
    private val binding get() = _binding!!

    private var answer = ""

    //질문 번호
    private var questionNum = 2

    //점수 합계
    private var totalScore = 0

    //선택 점수
    private var questionScore = 0

    //정답 배열
    var answerArray = ArrayList<String>()

    //질문 배열
    val questionArray = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTest2To18Binding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//
//        val bundle = arguments
//        val receivedData1 = bundle!!.getInt("totalScore1")
//        val receivedData2 = bundle!!.getString("answer1")
//
//        totalScore+=receivedData1
//
//        answerArray.add(receivedData2!!)


        questionArray.add(
            "우리 아이가 손발을 가만히 \n" +
                    "두지 못하거나 의자에 앉아서도 몸을 꼼지락 거리나요?"
        )

        questionArray.add(
            "우리 아이가 놀이를 할 때 \n" +
                    "지속적으로 집중하는 것에\n" +
                    "어려워 하나요?"
        )
        questionArray.add(
            "우리 아이가 자리에 앉아 있어야 하는 교실이나 다른 상황에서 \n" +
                    "앉아 있지를 못하나요?"
        )
        questionArray.add(
            "우리 아이가 다른 사람과 \n" +
                    "마주보고 이야기할 때 경청하지 않는 것처럼 보이나요?"
        )
        questionArray.add(
            "우리 아이가 그렇게 행동하면\n" +
                    "안 되는 상황에서 지나치게 뛰어다니거나 통제가 되지 않나요?"
        )
        questionArray.add(
            "우리 아이가 누군가의 말을 \n" +
                    "잘 따르지 않고, 일을 제때\n" +
                    "끝내지 못하나요?"
        )
        questionArray.add(
            "우리 아이가 여가 활동이나 \n" +
                    "재미있는 활동에 조용히 \n" +
                    "참여하기 어려워하나요?"
        )
        questionArray.add("우리 아이가 학교 공부나 숙제를 체계적으로 하지 못하나요?")
        questionArray.add(
            "우리 아이가 항상 끊임없이 \n" +
                    "움직이거나 마치 모터가 달려서 움직이는 것처럼 행동하나요?"
        )
        questionArray.add(
            "우리 아이가 지속적인 노력이 \n" +
                    "필요한 학교 공부나 숙제를 \n" +
                    "하지 않으려 하나요?"
        )
        questionArray.add(
            "우리 아이가 지나치게 \n" +
                    "말을 많이 하는 편인가요?"
        )
        questionArray.add(
            "우리 아이가 학교 공부나 숙제를 할 때 필요한 물건들을 \n" +
                    "잘 잃어버리나요?"
        )
        questionArray.add("우리 아이가 질문이 다 끝나기도 전에 성급하게 대답하나요?")
        questionArray.add("우리 아이가 쉽게 산만해 지나요?")
        questionArray.add("우리 아이가 차례를 기다리는데 어려움을 많이 느끼나요?")
        questionArray.add(
            "우리 아이가 일상적으로 하는\n" +
                    "일을 잘 잊어버리나요?"
        )
        questionArray.add("우리 아이가 다른 사람을 방해하거나 간섭하나요?")


        //progressbar 배열 선언


        //나가기 클릭
        binding.textExit.setOnClickListener {
            //점수 초기화
            viewModel.viewModelscore =0
            //이동
            val dialog = ExitDialog(context as AppCompatActivity)
            dialog.initDialog()
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

            isCheck = true
            answer = "전혀 그렇지 않다"
            questionScore = 0

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

            isCheck = true
            answer = "가끔 그렇다"
            questionScore = 1

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

            isCheck = true
            answer = "자주 그렇다"
            questionScore = 2

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

            isCheck = true
            answer = "매우 자주 그렇다"
            questionScore = 3

            if (isCheck == true) {

                binding.btnNextColor.setTextColor(Color.parseColor("#FFFFFF"))
                binding.nextColor.setBackgroundColor(Color.parseColor("#00C67B"))

            }
        }

        //다음 클릭
        binding.btnNext.setOnClickListener {



            if (isCheck == true) {


                questionNum++

                if (questionNum <= 18) {


                    //질문 숫자 변화
                    binding.textQuestionNum.setText("${questionNum}/18")


                    //progressBar 변화

                    if (questionNum == 3) {
                        binding.progressBar.setImageResource(R.drawable.progress3)
                    } else if (questionNum == 4) {
                        binding.progressBar.setImageResource(R.drawable.progress4)
                    } else if (questionNum == 5) {
                        binding.progressBar.setImageResource(R.drawable.progress5)
                    } else if (questionNum == 6) {
                        binding.progressBar.setImageResource(R.drawable.progress6)
                    } else if (questionNum == 7) {
                        binding.progressBar.setImageResource(R.drawable.progress7)
                    } else if (questionNum == 8) {
                        binding.progressBar.setImageResource(R.drawable.progress8)
                    } else if (questionNum == 9) {
                        binding.progressBar.setImageResource(R.drawable.progress9)
                    } else if (questionNum == 10) {
                        binding.progressBar.setImageResource(R.drawable.progress10)
                    } else if (questionNum == 11) {
                        binding.progressBar.setImageResource(R.drawable.progress11)
                    } else if (questionNum == 12) {
                        binding.progressBar.setImageResource(R.drawable.progress12)
                    } else if (questionNum == 13) {
                        binding.progressBar.setImageResource(R.drawable.progress13)
                    } else if (questionNum == 14) {
                        binding.progressBar.setImageResource(R.drawable.progress14)
                    } else if (questionNum == 15) {
                        binding.progressBar.setImageResource(R.drawable.progress15)
                    } else if (questionNum == 16) {
                        binding.progressBar.setImageResource(R.drawable.progress16)
                    } else if (questionNum == 17) {
                        binding.progressBar.setImageResource(R.drawable.progress17)
                    } else if (questionNum == 18) {
                        binding.progressBar.setImageResource(R.drawable.progress18)
                    }

                    //질문 변화
                    binding.textQuestion.setText(questionArray.get(questionNum - 2))


                    //총 점수 변화
//                    totalScore += questionScore

                    viewModel.addviewModelscore(questionScore)

                    viewModel.addviewModelScoreList(questionScore)
                    //정답 저장

                    viewModel.addviewModelAnswerList(answer)
                    Log.d("viewModel", "${viewModel.viewModelAnswerList} " +
                            " \n ${viewModel.viewModelScoreList}")
                    ////
                    binding.checkAnswer3.setImageResource(R.drawable.uncheck)
                    binding.checkAnswer1.setImageResource(R.drawable.uncheck)
                    binding.checkAnswer2.setImageResource(R.drawable.uncheck)
                    binding.checkAnswer0.setImageResource(R.drawable.uncheck)


                    binding.answer3Color.setBackgroundColor(Color.parseColor("#ffffff"))
                    binding.answer1Color.setBackgroundColor(Color.parseColor("#ffffff"))
                    binding.answer2Color.setBackgroundColor(Color.parseColor("#ffffff"))
                    binding.answer0Color.setBackgroundColor(Color.parseColor("#ffffff"))


//                    answerArray.add(answer)
                    isCheck = false
                    binding.btnNextColor.setTextColor(Color.parseColor("#000000"))
                    binding.nextColor.setBackgroundColor(Color.parseColor("#E8EBF0"))



                } else if (questionNum == 19) {

//
//                    totalScore += questionScore
//                    answerArray.add(answer)

                    viewModel.addviewModelscore(questionScore)

                    //정답 저장
                    viewModel.addviewModelScoreList(questionScore)
                    viewModel.addviewModelAnswerList(answer)
                    Log.d("viewModel", "${viewModel.viewModelAnswerList} " +
                            " \n ${viewModel.viewModelScoreList}")
                    //종료 페이지

                    val result1Fragment = Result1Fragment()
                    fragmentManager?.beginTransaction()?.apply {
                        replace(R.id.frameLayout, result1Fragment)
                        addToBackStack(null)
                        commit()
                    }

                }


            }


        }

        //이전 버튼
        binding.btnBack.setOnClickListener {

            if(questionNum==2) {

                //제거 로직
                viewModel.viewModelAnswerList.removeLast()
                viewModel.viewModelScoreList.removeLast()

                val testFragment = TestFragment()

                Log.d("viewModel", "${viewModel.viewModelAnswerList} " +
                        " \n ${viewModel.viewModelScoreList}")

                fragmentManager?.beginTransaction()?.apply {
                    replace(R.id.frameLayout, testFragment)
                    addToBackStack(null)
                    commit()
                }

            } else {
                //이전 번호
                questionNum--

                binding.textQuestionNum.setText("${questionNum}/18")

                if(questionNum==2) {
                    binding.progressBar.setImageResource(R.drawable.progress2)
                } else if (questionNum == 3) {
                    binding.progressBar.setImageResource(R.drawable.progress3)
                } else if (questionNum == 4) {
                    binding.progressBar.setImageResource(R.drawable.progress4)
                } else if (questionNum == 5) {
                    binding.progressBar.setImageResource(R.drawable.progress5)
                } else if (questionNum == 6) {
                    binding.progressBar.setImageResource(R.drawable.progress6)
                } else if (questionNum == 7) {
                    binding.progressBar.setImageResource(R.drawable.progress7)
                } else if (questionNum == 8) {
                    binding.progressBar.setImageResource(R.drawable.progress8)
                } else if (questionNum == 9) {
                    binding.progressBar.setImageResource(R.drawable.progress9)
                } else if (questionNum == 10) {
                    binding.progressBar.setImageResource(R.drawable.progress10)
                } else if (questionNum == 11) {
                    binding.progressBar.setImageResource(R.drawable.progress11)
                } else if (questionNum == 12) {
                    binding.progressBar.setImageResource(R.drawable.progress12)
                } else if (questionNum == 13) {
                    binding.progressBar.setImageResource(R.drawable.progress13)
                } else if (questionNum == 14) {
                    binding.progressBar.setImageResource(R.drawable.progress14)
                } else if (questionNum == 15) {
                    binding.progressBar.setImageResource(R.drawable.progress15)
                } else if (questionNum == 16) {
                    binding.progressBar.setImageResource(R.drawable.progress16)
                } else if (questionNum == 17) {
                    binding.progressBar.setImageResource(R.drawable.progress17)
                } else if (questionNum == 18) {
                    binding.progressBar.setImageResource(R.drawable.progress18)
                }


                //질문 변화
                binding.textQuestion.setText(questionArray.get(questionNum - 2))

                binding.checkAnswer3.setImageResource(R.drawable.uncheck)
                binding.checkAnswer1.setImageResource(R.drawable.uncheck)
                binding.checkAnswer2.setImageResource(R.drawable.uncheck)
                binding.checkAnswer0.setImageResource(R.drawable.uncheck)


                binding.answer3Color.setBackgroundColor(Color.parseColor("#ffffff"))
                binding.answer1Color.setBackgroundColor(Color.parseColor("#ffffff"))
                binding.answer2Color.setBackgroundColor(Color.parseColor("#ffffff"))
                binding.answer0Color.setBackgroundColor(Color.parseColor("#ffffff"))


                isCheck = false
                binding.btnNextColor.setTextColor(Color.parseColor("#000000"))
                binding.nextColor.setBackgroundColor(Color.parseColor("#E8EBF0"))


                //제거 로직
                viewModel.viewModelAnswerList.removeLast()
                viewModel.viewModelScoreList.removeLast()
                Log.d("viewModel", "${viewModel.viewModelAnswerList} " +
                        " \n ${viewModel.viewModelScoreList}")
            }
            }


    }


}