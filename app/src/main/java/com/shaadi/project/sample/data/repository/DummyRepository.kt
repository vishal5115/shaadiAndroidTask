package com.shaadi.project.sample.data.repository

import com.shaadi.project.sample.data.local.db.DatabaseService
import com.shaadi.project.sample.data.model.Result
import com.shaadi.project.sample.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

class DummyRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchDummy(): Single<List<Result>> =
        networkService.doDummyCall().map { it.results }

}