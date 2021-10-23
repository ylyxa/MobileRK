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
    @Json(name = "time") val time : Int,
    @Json(name = "high") val high : Float,
    @Json(name = "low") val low : Float
)