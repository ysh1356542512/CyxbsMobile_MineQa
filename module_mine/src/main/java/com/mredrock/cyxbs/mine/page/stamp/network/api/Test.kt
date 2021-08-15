package com.mredrock.cyxbs.mine.page.stamp.network.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
val url = "eyJEYXRhIjp7ImdlbmRlciI6IueUtyIsInN0dV9udW0iOiIyMDE5MjExNjg1In0sIkRvbWFpbiI6Im1hZ2lwb2tlIiwiUmVkaWQiOiI1NGUxNzEzZTQ5MjUyMTcyNmMzZGQ3ZTg4Mzk1NDcxNzJhZTk1ZTlhIiwiZXhwIjoiNzM5ODg0NjUxNiIsImlhdCI6IjE2MjkwMzUyNjMiLCJzdWIiOiJ3ZWIifQ==.khFmogDBth+sNU2I/lfSmvQZQQeGStdPcfqw7mu1oQ7vS1JZA+EmDAbfksu+5zkJYRUKlm0pqPc5VleYJpV4YndP5FMjE5WsOR4hcXs5Vwl49f4c/mYyKqQ5d3vbhlpvQnOhaBzCbHrjA1/gFM2zUmkuLmEryReMBwzz0L8YqWOZmaBYvifnl9dcoIF2OJnxEx/jn8IuMEBdKbPepzD7m57pOtD0ROJEsMqnIZGYpUVxdJklb/c8CiVO+GnqQkENAcxl2HrWZpZY6P2j4j0Ooghw8wdrQwgFKpK/7I36BIkmb7o06NHCmBG8B6JYxzcJkp/etzKttWil6/OgnkJDTw=="
fun provideRetrofit(): Retrofit {
    val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .addInterceptor{
            val build = it.request()
                .newBuilder()
                .addHeader("Authorization",
                    "Bearer $url")
                .build()
            return@addInterceptor it.proceed(build)
        }
        .build()

    return Retrofit.Builder()
        .baseUrl("https://be-dev.redrock.cqupt.edu.cn")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}