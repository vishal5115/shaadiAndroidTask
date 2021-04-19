package com.shaadi.project.sample

import android.app.Application
import com.shaadi.project.sample.di.component.ApplicationComponent
import com.shaadi.project.sample.di.component.DaggerApplicationComponent
import com.shaadi.project.sample.di.module.ApplicationModule

class InstagramApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}