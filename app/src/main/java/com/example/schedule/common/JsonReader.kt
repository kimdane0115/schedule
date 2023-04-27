package com.example.schedule.common

import android.util.Log
import org.json.JSONObject

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
            if (preDate != date) {
                schData.date = date
                schData.driveStart = "운행 시작"
            } else {
                schData.date = ""
            }
            schData.startTime = startTime
            schData.endTime = endTime

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
                stationData.time = time
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
}