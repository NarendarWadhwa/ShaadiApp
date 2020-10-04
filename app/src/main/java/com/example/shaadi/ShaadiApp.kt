package com.example.shaadi

import android.app.Application
import com.facebook.stetho.Stetho

class ShaadiApp : Application() {

    companion object {
        lateinit var instance: ShaadiApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        instance = this
    }
}