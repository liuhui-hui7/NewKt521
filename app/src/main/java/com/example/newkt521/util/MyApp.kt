package com.example.newkt521.util

import android.app.Application
import android.content.SharedPreferences

class MyApp : Application() {
    companion object{
        lateinit var sp: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        sp=getSharedPreferences("user", MODE_PRIVATE)
    }
}