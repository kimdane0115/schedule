package com.example.schedule.ui.day_schedule.detail

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.common.Station
import com.example.schedule.common.database.AppDatabase
import com.example.schedule.common.database.StationData
import com.example.schedule.databinding.DaySchDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DaySchDetailViewAdapter(private val context: Context): RecyclerView.Adapter<DaySchDetailViewAdapter.MyViewHolder>() {

    var stationlist = mutableListOf<Station>()//리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가
    private var preDate: String = ""
    inner class MyViewHolder(private val binding: DaySchDetailBinding): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(station: Station) {

            binding.name.text = station.name

//            var stationData = StationData(station.arriveTime, "", station.name)
            CoroutineScope(Dispatchers.IO).launch {
                var stationData = AppDatabase.getInstance(context)?.stationDao()?.getStationArriveTime(station.arriveTime)
                if (stationData != null) {
                    binding.name.text = stationData.name
                    if (!stationData.arriveTime.isEmpty()) {
                        binding.arriveTime.text = "도착 : ${stationData.arriveTime}"
//                        binding.btnArrive.isEnabled = false
                        binding.btnArrive.text = "도착 완료"
                        binding.btnArrive.setBackgroundColor(Color.GRAY)
                    }
                    if (!stationData.startTime.isEmpty()) {
                        binding.startTime.text = "출발 : ${stationData.startTime}"
//                        binding.btnArrive.isEnabled = false
                        binding.btnStart.text = "출발 완료"
//                        binding.btnStart.setBackgroundColor(Color.GRAY)
                    }

                    Log.d("testkimdw", "stationData : ${stationData.arriveTime}")
                }
            }

            binding.btnArrive.setOnClickListener { view ->
                binding.btnArrive.text = "도착 완료"
                binding.arriveTime.text = "도착 : ${station.arriveTime}"
                var stationData = StationData(station.arriveTime, "", station.name)

                CoroutineScope(Dispatchers.IO).launch {
                    val db = AppDatabase.getInstance(context)
                    db?.stationDao()?.insert(stationData)
                    Log.d("testkimdw", "insert Success!!!")
                }
            }
            binding.btnStart.setOnClickListener { view ->
                binding.btnStart.text = "출발 완료"
                binding.startTime.text = "출발 : ${station.startTime}"

                var stationData = StationData(station.arriveTime, station.startTime, station.name)

                CoroutineScope(Dispatchers.IO).launch {
                    val db = AppDatabase.getInstance(context)
                    db?.stationDao()?.update(stationData)
                    Log.d("testkimdw", "update Success!!!")
                }
            }
        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DaySchDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

//    override fun getItemCount(): Int = datalist.size
    override fun getItemCount(): Int = stationlist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.bind(datalist[position])
        holder.bind(stationlist[position])
    }
}