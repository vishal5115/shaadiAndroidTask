package com.shaadi.project.sample.di.component

import com.shaadi.project.sample.di.ActivityScope
import com.shaadi.project.sample.di.module.ActivityModule
import com.shaadi.project.sample.ui.dummy.DummyActivity
import com.shaadi.project.sample.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: SplashActivity)


    fun inject(activity: DummyActivity)
}