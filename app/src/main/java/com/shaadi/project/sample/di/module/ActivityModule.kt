package com.shaadi.project.sample.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaadi.project.sample.data.local.db.DatabaseService
import com.shaadi.project.sample.data.repository.DummyRepository
import com.shaadi.project.sample.data.repository.UserRepository
import com.shaadi.project.sample.ui.base.BaseActivity
import com.shaadi.project.sample.ui.dummies.DummiesAdapter
import com.shaadi.project.sample.ui.dummy.DummyViewModel
import com.shaadi.project.sample.ui.splash.SplashViewModel
import com.shaadi.project.sample.utils.ViewModelProviderFactory
import com.shaadi.project.sample.utils.network.NetworkHelper
import com.shaadi.project.sample.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */
@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideSplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): SplashViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
            //this lambda creates and return SplashViewModel
        }).get(SplashViewModel::class.java)

    @Provides
    fun provideDummiesAdapter() = DummiesAdapter(activity.lifecycle, ArrayList())

    @Provides
    fun provideDummyViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        databaseService: DatabaseService,
        dummyRepository: DummyRepository
    ): DummyViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(DummyViewModel::class) {
            DummyViewModel(schedulerProvider, compositeDisposable, networkHelper,databaseService,dummyRepository)
        }).get(DummyViewModel::class.java)

}