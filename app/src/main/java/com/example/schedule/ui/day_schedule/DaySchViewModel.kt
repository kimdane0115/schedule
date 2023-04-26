package com.example.schedule.ui.day_schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DaySchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Day Schedule"
    }
    val text: LiveData<String> = _text
    val mListDatas = mutableListOf<DaySchData>()

    fun initList() : List<DaySchData> {
        with(mListDatas) {
            add(DaySchData("오전 10 시 30 분", "오전 11 시 00 분"))
            add(DaySchData("오전 11 시 30 분", "오후 12 시 00 분"))
            add(DaySchData("오후 12 시 30 분", "오후 01 시 00 분"))
        }
        return mListDatas
    }
}