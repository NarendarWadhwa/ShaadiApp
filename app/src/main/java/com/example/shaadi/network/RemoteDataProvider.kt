package com.example.shaadi.network

import com.example.shaadi.network.models.UserProfile
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

interface RemoteDataProvider {

    fun getUsersProfile(
        results: String,
        success: Consumer<ArrayList<UserProfile>>,
        error: Consumer<Throwable>
    ): Disposable

}