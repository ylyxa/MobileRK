package com.example.mobilerk

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://min-api.cryptocompare.com/data/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WebApiService {
    @GET("histoday")
    fun getData(
        @Query("fsym") curr: String,
        @Query("tsym") curr_: String,
        @Query("limit") limit: Int
    ): WebData
}

object WebApi {
    val retrofitService: WebApiService by lazy { retrofit.create(WebApiService::class.java) }
}