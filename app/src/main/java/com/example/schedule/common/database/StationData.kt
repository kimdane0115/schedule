package com.example.schedule.common.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stations")
data class StationData(
//    @PrimaryKey val id: Int,
//    val date: String,
    @PrimaryKey val arriveTime: String,
    val startTime: String,
    val name: String
)
