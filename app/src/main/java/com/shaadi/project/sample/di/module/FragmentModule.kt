package com.shaadi.project.sample.di.module

import androidx.recyclerview.widget.LinearLayoutManager
import com.shaadi.project.sample.ui.base.BaseFragment
import com.shaadi.project.sample.ui.dummies.DummiesAdapter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)


    @Provides
    fun provideDummiesAdapter() = DummiesAdapter(fragment.lifecycle, ArrayList())
}