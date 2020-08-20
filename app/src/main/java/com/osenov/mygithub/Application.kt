package com.osenov.mygithub

import android.app.Application
import com.osenov.mygithub.di.component.ApplicationComponent
import com.osenov.mygithub.di.component.DaggerApplicationComponent
import com.osenov.mygithub.di.module.ApplicationModule

class Application : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}