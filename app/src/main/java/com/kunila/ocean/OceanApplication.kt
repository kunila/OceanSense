package com.kunila.ocean

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OceanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize any dependencies or configurations here
    }

}