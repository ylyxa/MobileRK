package com.example.mobilerk

import com.squareup.moshi.Json

data class WebData (
    @Json(name = "Response") val response : String,
    @Json(name = "Message") val message : String,
    @Json(name = "Data") val days : DaysData
)

data class DaysData (
    @Json(name = "Data") val days : List<DayData>
)

data class DayData (
    val time: Int,
    val high: Float,
    val low: Float,
    val open: Float,
    val volumefrom: Float,
    val volumeto: Float,
    val close: Float,
)