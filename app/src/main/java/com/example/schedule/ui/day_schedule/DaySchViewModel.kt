package com.example.schedule.ui.day_schedule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schedule.common.ScheduleData
import com.example.schedule.common.ScheduleDataList

class DaySchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Day Schedule"
    }
    val text: LiveData<String> = _text
//    val mSchDatas = mutableListOf<ScheduleData>()

    fun getList(): MutableList<ScheduleData> {
        Log.d("testkimdw", "size: ${ScheduleDataList.instance.scheduleDataList.size}")
//        for (el: ScheduleData in ScheduleDataList.instance.scheduleDataList) {
//            for (map in el.stationMap) {
//                Log.d("testkimdw", "key : ${map.key}")
//                for (value in map.value) {
//                    Log.d("testkimdw", "item : ${value.time}")
//                    Log.d("testkimdw", "item : ${value.name}")
//                }
//            }
//        }

        return ScheduleDataList.instance.scheduleDataList
    }
}