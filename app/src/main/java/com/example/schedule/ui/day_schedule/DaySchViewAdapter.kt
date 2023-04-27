package com.example.schedule.ui.day_schedule

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.MainActivity
import com.example.schedule.common.ScheduleData
import com.example.schedule.databinding.DaySchListBinding

class DaySchViewAdapter: RecyclerView.Adapter<DaySchViewAdapter.MyViewHolder>() {

//    var datalist = mutableListOf<DaySchData>()//리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가
    var schlist = mutableListOf<ScheduleData>()//리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가
    private var preDate: String = ""
    inner class MyViewHolder(private val binding: DaySchListBinding): RecyclerView.ViewHolder(binding.root) {

//        fun bind(schData : DaySchData){
//            binding.startTime.text = "운행 시간 : " +schData.drive_startTime
//            binding.arriveTime.text = "도착 시간 : " +schData.school_arriveTime
//        }

        fun bind(schData: ScheduleData) {
            binding.date.text = schData.date
            if (schData.driveStart.isEmpty())
                binding.driveStart.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.driveStart.text = schData.driveStart
            binding.startTime.text = "운행 시작 : " + schData.startTime
            binding.arriveTime.text = "도착 시간 : " + schData.endTime
            preDate = schData.date
        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DaySchListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

//    override fun getItemCount(): Int = datalist.size
    override fun getItemCount(): Int = schlist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.bind(datalist[position])
        holder.bind(schlist[position])
    }
}