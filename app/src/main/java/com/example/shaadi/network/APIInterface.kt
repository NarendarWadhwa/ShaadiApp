package com.example.shaadi.network

import com.example.shaadi.network.models.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("api/")
    fun getUsersProfile(
        @Query("results") results: String
    ): Single<UserResponse>

}