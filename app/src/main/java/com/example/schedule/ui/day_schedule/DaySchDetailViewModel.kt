package com.example.schedule.ui.day_schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DaySchDetailViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "오전 10시 30분 -> 오전 11시 00분"
    }
    val text: LiveData<String> = _text
}