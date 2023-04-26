package com.example.schedule.ui.day_schedule

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.example.schedule.MainActivity
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
        val mActivity = activity as MainActivity
        val daySchViewModel =
            ViewModelProvider(this).get(DaySchViewModel::class.java)

        _binding = FragmentDayschBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val adapter = RecyclerViewAdapter()
//        adapter.datalist = daySchViewModel.initializelist() as MutableList<DogData>;
//        _binding!!.recyclerView.adapter = adapter
//        binding!!.recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = DaySchViewAdapter()
        adapter.datalist = daySchViewModel.initList() as MutableList<DaySchData>
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.recyclerView.addOnItemTouchListener(object : OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val child = rv.findChildViewUnder(e.x, e.y)
                val position = rv.getChildAdapterPosition(child!!)
                Log.d("position", "[$position]")
                mActivity.goToDaySchDetail()
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
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