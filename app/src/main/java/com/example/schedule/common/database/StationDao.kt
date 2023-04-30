package com.example.schedule.common.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StationDao {
    @Insert
    fun insert(stationData: StationData)

    @Update
    fun update(stationData: StationData)

    @Query("SELECT * FROM stations WHERE arriveTime = :time")
    fun getStationArriveTime(time: String): StationData

    @Query("SELECT * FROM stations WHERE startTime = :time")
    fun getStationStartTime(time: String): StationData
}