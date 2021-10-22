package com.example.mobilerk

import com.squareup.moshi.Json

data class WebData (
    @Json(name = "userId") val userId : Int,
    @Json(name = "id") val id : Int,
    @Json(name = "title") val title : String
    )