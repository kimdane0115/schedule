package com.example.schedule.ui.day_schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DaySchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Day Schedule"
    }
    val text: LiveData<String> = _text
}