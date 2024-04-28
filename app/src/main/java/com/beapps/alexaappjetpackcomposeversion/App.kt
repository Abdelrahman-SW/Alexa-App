package com.beapps.alexaappjetpackcomposeversion

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import net.gotev.speech.Speech

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Speech.init(applicationContext, packageName);
        Speech.getInstance().say("")
    }
}