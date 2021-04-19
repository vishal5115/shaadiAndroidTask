package com.shaadi.project.sample.di.component

import com.shaadi.project.sample.di.ViewModelScope
import com.shaadi.project.sample.di.module.ViewHolderModule
import com.shaadi.project.sample.ui.dummies.DummyItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: DummyItemViewHolder)
}