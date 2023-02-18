package com.nux.studio.studtourism.app

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("67b9652a-e293-4806-9cf7-7f28e040c3d1");
    }
}