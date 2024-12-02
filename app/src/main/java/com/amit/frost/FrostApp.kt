package com.amit.frost

import android.app.Application
import com.amit.frost.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FrostApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FrostApp)
            androidLogger()

            modules(appModule)
        }
    }
}