package com.shaadi.project.sample.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.shaadi.project.sample.BuildConfig
import com.shaadi.project.sample.InstagramApplication
import com.shaadi.project.sample.data.local.db.DatabaseService
import com.shaadi.project.sample.data.remote.NetworkService
import com.shaadi.project.sample.data.remote.Networking
import com.shaadi.project.sample.di.ApplicationContext
import com.shaadi.project.sample.utils.network.NetworkHelper
import com.shaadi.project.sample.utils.rx.RxSchedulerProvider
import com.shaadi.project.sample.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: InstagramApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences("project-sample-project-prefs", Context.MODE_PRIVATE)

    /**
     * We need to write @Singleton on the provide method if we are create the instance inside this method
     * to make it singleton. Even if we have written @Singleton on the instance's class
     */
    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "project-sample-project-db"
        ).build()

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.API_KEY,
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}