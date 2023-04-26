package com.example.schedule.ui.day_schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DaySchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Day Schedule"
    }
    val text: LiveData<String> = _text
    val mDatas = mutableListOf<DogData>()

    fun initializelist() : List<DogData> { //임의로 데이터 넣어서 만들어봄
        with(mDatas){
            add(DogData("","dog1",20,"M"))
            add(DogData("","dog2",20,"M"))
            add(DogData("","dog3",20,"M"))
            add(DogData("","dog4",20,"M"))
            add(DogData("","dog5",20,"M"))
            add(DogData("","dog2",20,"M"))
            add(DogData("","dog6",20,"M"))
            add(DogData("","dog7",20,"M"))
            add(DogData("","dog8",20,"M"))
            add(DogData("","dog9",20,"M"))
            add(DogData("","dog10",20,"M"))
            add(DogData("","dog11",20,"M"))
            add(DogData("","dog12",20,"M"))
        }
        return mDatas
    }
}