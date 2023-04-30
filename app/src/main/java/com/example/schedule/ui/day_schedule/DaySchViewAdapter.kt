package com.example.schedule.ui.day_schedule

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.common.ScheduleData
import com.example.schedule.databinding.DaySchListBinding
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class DaySchViewAdapter: RecyclerView.Adapter<DaySchViewAdapter.MyViewHolder>() {

    var schlist = mutableListOf<ScheduleData>()//리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가
    private var timerJob: Job? = null
    inner class MyViewHolder(private val binding: DaySchListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(schData: ScheduleData) {
            binding.date.text = schData.date
            Log.d("testkimdw", "date : ${schData.date}")
            if (schData.driveStart.isEmpty())
                binding.driveStart.visibility = View.INVISIBLE
            binding.driveStart.text = schData.driveStart
            binding.startTime.text = "운행 시작 : " + schData.startTime
            binding.arriveTime.text = "도착 시간 : " + schData.endTime

            timerJob?.cancel()

            GlobalScope.launch(Dispatchers.Main) {
                // 1초마다 실행되는 타이머 구현
                timerJob = launch {
                    while (true) {
                        delay(1000)
                        if (schData.driveStart.isEmpty()) {
                            if (checkStartTime(schData.startDateTime)) {
                                binding.driveStart.visibility = View.VISIBLE
                                schData.driveStart = "운행 시작"
                                notifyDataSetChanged()
                            }
                            Log.d("testkimdw", "startDateTime : ${schData.startDateTime}")
                        }
                    }
                }
            }

        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DaySchListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = schlist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.bind(datalist[position])
        holder.bind(schlist[position])
    }

    fun checkStartTime( currentTime : String) : Boolean {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, - 30)
        val thirtyMinutesAgo = calendar.timeInMillis

        val dateString = currentTime // 변환할 날짜 문자열
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm") // 입력된 날짜 문자열 형식
        val date = format.parse(dateString) // 입력된 문자열을 Date 객체로 변환
        Log.d("testkimdw", "date : ${date}")
        val millis = date.time

        if ( millis <= thirtyMinutesAgo ) {
            return true
        }
        return false
    }
}