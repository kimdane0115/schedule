package com.example.schedule.ui.month_schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MonthSchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "준비 중입니다."
    }
    val text: LiveData<String> = _text
}