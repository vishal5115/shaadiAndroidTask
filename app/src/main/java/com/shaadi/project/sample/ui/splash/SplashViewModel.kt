package com.shaadi.project.sample.ui.splash

import androidx.lifecycle.MutableLiveData
import com.shaadi.project.sample.data.repository.UserRepository
import com.shaadi.project.sample.ui.base.BaseViewModel
import com.shaadi.project.sample.utils.common.Event
import com.shaadi.project.sample.utils.network.NetworkHelper
import com.shaadi.project.sample.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    // Event is used by the view model to tell the activity to launch another Activity
    // view model also provided the Bundle in the event that is needed for the Activity
    val launchDummy: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val launchLogin: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {
        // Empty map of key and serialized value is passed to Activity in Event that is needed by the other Activity
        launchDummy.postValue(Event(emptyMap()))
    }
}