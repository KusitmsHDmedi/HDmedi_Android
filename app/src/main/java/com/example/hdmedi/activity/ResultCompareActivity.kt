package com.example.hdmedi.activity

import android.os.Bundle
import com.example.hdmedi.CompareResultAdapter
import com.example.hdmedi.Data
import com.example.hdmedi.R
import com.example.hdmedi.databinding.ActivityResultCompareBinding

class ResultCompareActivity : BaseActivity<ActivityResultCompareBinding>(R.layout.activity_result_compare) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val item = ArrayList<Data>()
        val question = resources.getStringArray(R.array.question)
        for(i in 0 until 18){
            item.add(Data(question[i], "hi", "hi"))
        }
        val adapter = CompareResultAdapter(item)
        binding.recyclerView.adapter = adapter
    }
}