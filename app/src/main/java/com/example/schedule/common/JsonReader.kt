package com.example.schedule.common

import android.util.Log
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class JsonReader {
    fun jsonParser(_str: String) {
        val schDataListInstance = ScheduleDataList.instance
        var schList = mutableListOf<ScheduleData>()

        val jsonObject = JSONObject(_str)
        val jsonArray = jsonObject.getJSONArray("schedules")

        var preDate = ""
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
//            Log.d("testkimdw", "jsonObject : ${jsonObject}")
            val date = jsonObject.getString("date")
            val startTime = jsonObject.getString("startTime")
            val endTime = jsonObject.getString("endTime")

            val schData = ScheduleData()
            schData.startDateTime = "$date $startTime"
            schData.startTime = startTime
            schData.endTime = endTime
            if (checkStartTime(schData.startDateTime))
                schData.driveStart = "운행 시작"
            if (preDate != date) {
                schData.date = date
            } else {
                schData.date = ""
            }

            var stationMapKey :String = date.split("-").joinToString("") + startTime.split(":").joinToString("")
            schData.sortIdx = stationMapKey.toLong()
            Log.d("testkimdw", "sortIdx : ${schData.sortIdx}")
            var stationList = mutableListOf<Station>()
            var stationMap = mutableMapOf<String, MutableList<Station>>()
            val jsonStationArr = jsonObject.getJSONArray("stations")
            for (j in 0 until jsonStationArr.length()) {
                val jsonStationObj = jsonStationArr.getJSONObject(j)
                val id = jsonStationObj.getInt("id")
                val name = jsonStationObj.getString("name")
                val index = jsonStationObj.getInt("index")
                val time = jsonStationObj.getString("time")

                val stationData = Station()
                stationData.id = id
                stationData.name = name
                stationData.index = index
                stationData.arriveTime = "$date $time"
                val format = SimpleDateFormat("yyyy-MM-dd HH:mm") // 입력된 날짜 문자열 형식
                val arr_date = format.parse(stationData.arriveTime)
                var millis = arr_date.time
                millis += ((1000 * 60) * 5)
                var date = Date(millis)
                val format2 = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                stationData.startTime = format2.format(date)
                stationList.add(stationData)
            }
            stationMap[stationMapKey] = stationList.toMutableList().apply { sortBy { it.index } }
//            schData.stationList = stationList
            schData.stationMap = stationMap
//            Log.d("testkimdw", "stationList size : ${schData.stationMap.size}")
            schList.add(schData)
            preDate = date
        }
//        Log.d("testkimdw", "sort :  ${schList.sortBy { it.sortIdx }}")
//        schList.toMutableList().apply { sortBy { it.sortIdx } }
        schDataListInstance.scheduleDataList = schList.toMutableList().apply { sortBy { it.sortIdx } }

        Log.d("testkimdw", "jsonParser size : ${schDataListInstance.scheduleDataList.size}")
//        Log.d("testkimdw", "jsonParser station size : ${schDataListInstance.scheduleDataList[0].stationList.size}")
    }

    fun checkStartTime( currentTime : String) : Boolean {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, - 30)
        val thirtyMinutesAgo = calendar.timeInMillis

        val dateString = currentTime // 변환할 날짜 문자열
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm") // 입력된 날짜 문자열 형식
        val date = format.parse(dateString) // 입력된 문자열을 Date 객체로 변환
        Log.d("testkimdw", "date : ${date}")
        val millis = date.time

        if ( millis <= thirtyMinutesAgo ) {
            return true
        }
        return false
    }
}