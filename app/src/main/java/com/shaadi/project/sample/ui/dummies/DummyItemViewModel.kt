package com.shaadi.project.sample.ui.dummies

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.shaadi.project.sample.data.local.db.entity.NewDummy
import com.shaadi.project.sample.ui.base.BaseItemViewModel
import com.shaadi.project.sample.utils.common.Resource
import com.shaadi.project.sample.utils.log.Logger
import com.shaadi.project.sample.utils.network.NetworkHelper
import com.shaadi.project.sample.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DummyItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseItemViewModel<NewDummy>(schedulerProvider, compositeDisposable, networkHelper) {

    companion object {
        const val TAG = "DummyItemViewModel"
    }

    val name: LiveData<String> = Transformations.map(data) { it.first_name+" "+it.last_name}
    val url: LiveData<String?> = Transformations.map(data) { it.photo }
    val email: LiveData<String?> = Transformations.map(data) { it.email }

    fun onItemClick(position: Int) {
        messageString.postValue(Resource.success("onItemClick at $position of ${data.value?.first_name}"))
        Logger.d(TAG, "onItemClick at $position")
    }

    override fun onCreate() {
        Logger.d(TAG, "onCreate called")
    }
}