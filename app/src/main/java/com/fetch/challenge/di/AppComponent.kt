package com.fetch.challenge.di

import com.fetch.challenge.presentation.MainActivity
import com.fetch.challenge.presentation.utils.ViewModelModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent  {
    fun inject(activity: MainActivity)
}