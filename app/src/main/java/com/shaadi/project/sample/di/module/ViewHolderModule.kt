package com.shaadi.project.sample.di.module

import androidx.lifecycle.LifecycleRegistry
import com.shaadi.project.sample.di.ViewModelScope
import com.shaadi.project.sample.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}