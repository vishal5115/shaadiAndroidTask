package com.shaadi.project.sample.di.component

import com.shaadi.project.sample.di.FragmentScope
import com.shaadi.project.sample.di.module.FragmentModule
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

}