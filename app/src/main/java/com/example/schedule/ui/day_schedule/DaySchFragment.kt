package com.example.schedule.ui.day_schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedule.databinding.FragmentDayschBinding

class DaySchFragment : Fragment() {

    private var _binding: FragmentDayschBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        initDogRecyclerView()
        val daySchViewModel =
            ViewModelProvider(this).get(DaySchViewModel::class.java)

        _binding = FragmentDayschBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = RecyclerViewAdapter()
        adapter.datalist = daySchViewModel.initializelist() as MutableList<DogData>;
        _binding!!.recyclerView.adapter = adapter
        binding!!.recyclerView.layoutManager = LinearLayoutManager(context)

        return root
    }

//    fun initDogRecyclerView(){
//        val adapter=RecyclerViewAdapter() //어댑터 객체 만듦
//        adapter.datalist=mDatas //데이터 넣어줌
//        binding.recyclerView.adapter=adapter //리사이클러뷰에 어댑터 연결
//        binding.recyclerView.layoutManager= LinearLayoutManager(context) //레이아웃 매니저 연결
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}