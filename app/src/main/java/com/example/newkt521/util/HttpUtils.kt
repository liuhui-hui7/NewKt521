package com.example.newkt521.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit


object HttpUtils {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(5,TimeUnit.SECONDS)  //链接超时5秒
        .readTimeout(5,TimeUnit.SECONDS)    //读取超时5秒
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
    val  retrofit = Retrofit.Builder().baseUrl("http://wq.bwstudent.com:7999 ")
        .client(okHttpClient)//和okhttpClient相关联
        .addConverterFactory(GsonConverterFactory.create())//添加转换类 解析为实体类
        .build()
    //使用Retrofit的create方法创建ApiService的实力
    val  apiService = retrofit.create<ApiService>()
}