package com.example.schedule.ui.day_schedule.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schedule.common.ScheduleDataList
import com.example.schedule.common.Station

class DaySchDetailViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "오전 10시 30분 -> 오전 11시 00분"
    }
    val text: LiveData<String> = _text

    fun getList(key : Long) : MutableList<Station>{
        var resList = mutableListOf<Station>()
        val schDataListInstance = ScheduleDataList.instance
        for (item in schDataListInstance.scheduleDataList) {
            if (key == item.sortIdx) {
                for (map in item.stationMap) {
                    resList = map.value
                    break;
                }
            }
        }
        return resList
    }
}