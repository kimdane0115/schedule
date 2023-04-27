package com.example.schedule.ui.day_schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.schedule.MainActivity
import com.example.schedule.databinding.FragmentDayschDetailBinding

class DaySchDetailFragment : Fragment() {

    private var _binding: FragmentDayschDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).supportActionBar?.hide()
        val daySchDetailViewModel =
            ViewModelProvider(this).get(DaySchDetailViewModel::class.java)

//        Log.d("testkimdw", "receive String : ${arguments?.getString("data")?.let { text -> Log.d("testkimdw", "text : ${text}") }}")
        Log.d("testkimdw", "receive idx : ${arguments?.getLong("idx")}")
        _binding = FragmentDayschDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.date
        daySchDetailViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
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