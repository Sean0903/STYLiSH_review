package com.sean.stylish_review.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val  BASE_URL = "https://api.appworks-school.tw/api/1.0/"

private val moshi  = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

object StylishApi {
    val retrofitService : StylishApiService by lazy { retrofit.create(StylishApiService::class.java) }
}

interface StylishApiService {

    @GET("marketing/hots")
    suspend fun getMarketingHots(): MarketingHotsResult
}