package com.tyryshkin.newsapp

import android.app.Application
import android.content.Context
import com.tyryshkin.newsapp.di.AppComponent
import com.tyryshkin.newsapp.di.DaggerAppComponent


class NewsApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is NewsApplication -> appComponent
        else -> applicationContext.appComponent
    }