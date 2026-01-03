package com.example.mycoursesapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyCoursesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}