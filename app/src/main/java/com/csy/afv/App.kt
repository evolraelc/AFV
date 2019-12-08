package com.csy.afv

import android.app.Application
import com.csy.afv.util.DisplayManager


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        DisplayManager.init(this)
    }
}