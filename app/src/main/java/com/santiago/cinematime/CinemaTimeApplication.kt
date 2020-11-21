package com.santiago.cinematime

import android.app.Application
import timber.log.Timber

class CinemaTimeApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        this.initDI()
    }
}