package com.example.schedule.common

import java.util.Date

class ScheduleData {
    var sortIdx: Long = 0
    var startDateTime: String = ""
    lateinit var date: String
    lateinit var startTime: String
    lateinit var endTime: String
    var driveStart: String = ""
//    var stationList: MutableList<Station> = mutableListOf()
    var stationMap = mutableMapOf<String, MutableList<Station>>()

}