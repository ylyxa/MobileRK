package com.example.mobilerk

import com.squareup.moshi.Json

data class WebData (
    @field:Json(name = "Data") val daysData : List<DataByDay>,
)

data class DataByDay (
    val time: Int,
    val high: Float,
    val low: Float,
    val open: Float,
    val volumefrom: Float,
    val volumeto: Float,
    val close: Float,
)