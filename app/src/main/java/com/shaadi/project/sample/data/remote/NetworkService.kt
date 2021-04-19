package com.shaadi.project.sample.data.remote

import com.shaadi.project.sample.data.model.ModelDataClass
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @GET(Endpoints.DUMMY)
    fun doDummyCall(
    ): Single<ModelDataClass>

    /*
     * Example to add other headers
     *
     *  @POST(Endpoints.DUMMY_PROTECTED)
        fun sampleDummyProtectedCall(
            @Body request: DummyRequest,
            @Header(Networking.HEADER_USER_ID) userId: String, // pass using UserRepository
            @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String, // pass using UserRepository
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY // default value set when Networking create is called
        ): Single<DummyResponse>
     */

}