package com.fetch.challenge.presentation

import android.app.Application
import com.fetch.challenge.di.AppComponent
import com.fetch.challenge.di.DaggerAppComponent

class FetchApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}