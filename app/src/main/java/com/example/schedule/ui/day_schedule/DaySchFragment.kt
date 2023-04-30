package com.example.schedule.ui.day_schedule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.example.schedule.MainActivity
import com.example.schedule.R
import com.example.schedule.common.ScheduleData
import com.example.schedule.databinding.FragmentDayschBinding
import kotlinx.coroutines.*
import kotlin.math.abs

class DaySchFragment : Fragment() {

    private var _binding: FragmentDayschBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var timerJob: Job? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mActivity = activity as MainActivity
        mActivity.supportActionBar?.show()
        val daySchViewModel = ViewModelProvider(this).get(DaySchViewModel::class.java)

        _binding = FragmentDayschBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val adapter = RecyclerViewAdapter()
//        adapter.datalist = daySchViewModel.initializelist() as MutableList<DogData>;
//        _binding!!.recyclerView.adapter = adapter
//        binding!!.recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = DaySchViewAdapter()
//        adapter.datalist = daySchViewModel.initList() as MutableList<DaySchData>
        adapter.schlist = daySchViewModel.getList()
//        adapter.datalist = daySchViewModel.getList()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.recyclerView.addOnItemTouchListener(object : OnItemTouchListener {
            private var touchDownX = 0f
            private var touchDownY = 0f
            private var isTap = true

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                when (e.actionMasked) {
                    MotionEvent.ACTION_MOVE -> {
                        if (isTap && (abs(e.x - touchDownX) > 10 || abs(e.y - touchDownY) > 10)) {
                            isTap = false
                            rv.stopScroll()
                        }
                    }
                    MotionEvent.ACTION_DOWN -> {
                        touchDownX = e.x
                        touchDownY = e.y
                        isTap = true
                    }
                    MotionEvent.ACTION_UP -> {
                        if (isTap) {
                            // 아이템 클릭 이벤트를 취소합니다.
                            val child = rv.findChildViewUnder(e.x, e.y)
                            val position = rv.getChildAdapterPosition(child!!)
                            mActivity.goToDaySchDetail(adapter.schlist[position].sortIdx)
                            return true
                        }
                    }
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}