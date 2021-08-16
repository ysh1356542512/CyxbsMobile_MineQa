package com.mredrock.cyxbs.mine.page.stamp.network.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
val url = "eyJEYXRhIjp7ImdlbmRlciI6IueUtyIsInN0dV9udW0iOiIyMDE5MjExNjg1In0sIkRvbWFpbiI6Im1hZ2lwb2tlIiwiUmVkaWQiOiI1NGUxNzEzZTQ5MjUyMTcyNmMzZGQ3ZTg4Mzk1NDcxNzJhZTk1ZTlhIiwiZXhwIjoiNzM5ODkwNjE4NyIsImlhdCI6IjE2MjkwOTQ5MzQiLCJzdWIiOiJ3ZWIifQ==.fU9+UnRRTxF4QwLPPXd8XL3RmykYGOtFluQ8uDj1j1A/WOjX1DcLpSZHbQ3V9jfv60k7NmDe8Caj5Ec/j+YFnJ4yRg3nxo+aqz48RQYxwScDOyRlMh/AslqXOkNJFxZgUd9gZzcjpZknuaLM1XaOQ9WgF/khFnEY2RWVLYPXuq3thWUWQPzcJiJhKJM+4vKl0o6gLnBxsmQkdas4LyimojooDu1ohP3oy3E8m/8KRepU89huaBoNTQ+j/TowdeIPemnfQT//H5yYhR8XjntQF5+nAUR00XZbH6M6HHH24drQQVeKu+Fy6/rzDPPflHEFzunfHBY7l3gKCUAL9Yk0UQ=="
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