package com.shaadi.project.sample.ui.dummy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.shaadi.project.sample.data.local.db.DatabaseService
import com.shaadi.project.sample.data.local.db.entity.DummyEntity
import com.shaadi.project.sample.data.local.db.entity.NewDummy
import com.shaadi.project.sample.data.repository.DummyRepository
import com.shaadi.project.sample.ui.base.BaseViewModel
import com.shaadi.project.sample.utils.common.Resource
import com.shaadi.project.sample.utils.common.Status
import com.shaadi.project.sample.utils.network.NetworkHelper
import com.shaadi.project.sample.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class DummyViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    var databaseService: DatabaseService,
    private val dummyRepository: DummyRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    var list: ArrayList<NewDummy> = ArrayList<NewDummy>()




    private val dummyLiveData: MutableLiveData<Resource<ArrayList<NewDummy>>> = MutableLiveData()

    fun getDummies(): LiveData<ArrayList<NewDummy>> =
        Transformations.map(dummyLiveData) {
            it.data
        }

    fun isDummiesFetching(): LiveData<Boolean> =
        Transformations.map(dummyLiveData) { it.status == Status.LOADING }

    override fun onCreate() {

        if (dummyLiveData.value == null && checkInternetConnectionWithMessage()) {
            dummyLiveData.postValue(Resource.loading())
            compositeDisposable.add(
                dummyRepository.fetchDummy()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                                for (i in it.indices) {
                                    list.add(NewDummy(it[i].name.first,it[i].name.last,it[i].email,it[i].picture.large))
                                    databaseService.dummyDao().insert(DummyEntity(it[i].location.street.number,
                                        it[i].name.first,it[i].name.last,it[i].picture.large,it[i].email,false))
                                    // ...
                                }

                            dummyLiveData.postValue(Resource.success(list))

                        },
                        {
                            handleNetworkError(it)
                            dummyLiveData.postValue(Resource.error())
                        })
            )
        }else{
            compositeDisposable.add(
                     databaseService.dummyDao()
                            .getAll().subscribeOn(schedulerProvider.io())
                         .subscribe(
                             {
                                 for (i in it.indices) {
                                     list.add(NewDummy(it[i].name,it[i].last,it[i].email,it[i].large))
                                 }
                                 dummyLiveData.postValue(Resource.success(list))
                             },{

                             }
                         )
            )
        }
    }
}