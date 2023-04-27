package com.example.schedule.common

class ScheduleDataList private constructor() {
    var scheduleDataList: MutableList<ScheduleData> = mutableListOf()

    companion object {
        val instance: ScheduleDataList by lazy(LazyThreadSafetyMode.PUBLICATION) { ScheduleDataList() }
    }

}