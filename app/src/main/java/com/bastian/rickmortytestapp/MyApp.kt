package com.bastian.rickmortytestapp

import android.app.Application
import com.bastian.rickmortytestapp.di.koinmodule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApp)
            modules(koinmodule)
        }
    }
}