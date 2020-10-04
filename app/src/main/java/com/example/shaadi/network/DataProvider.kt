package com.example.shaadi.network

import com.example.shaadi.R
import com.example.shaadi.ShaadiApp
import com.example.shaadi.db.ShaadiDatabase
import com.example.shaadi.network.models.UserProfile
import com.example.shaadi.utils.isNetworkAvailable
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

object DataProvider : RemoteDataProvider {

    private val mServices: APIInterface by lazy {
        APIClient.getClient().create(APIInterface::class.java)
    }
    private val mUserProfileDao by lazy {
        ShaadiDatabase.getDb(ShaadiApp.instance)!!.userProfileDao()
    }

    private fun noInternetAvailable(error: Consumer<Throwable>) =
        error.accept(Throwable(ShaadiApp.instance.getString(R.string.no_internet_connection)))

    private fun getDefaultDisposable(): Disposable = object : Disposable {
        override fun isDisposed() = false
        override fun dispose() {}
    }

    override fun getUsersProfile(
        results: String,
        success: Consumer<ArrayList<UserProfile>>,
        error: Consumer<Throwable>
    ): Disposable = getUserProfileFromDb().subscribe(Consumer { userProfileList ->
        if (userProfileList.isNotEmpty()) {
            success.accept(userProfileList as ArrayList<UserProfile>?)
        } else {
            getUserProfileFromNetwork(results, success, error)
        }
    }, error)

    private fun getUserProfileFromDb(): Single<List<UserProfile>> {
        return mUserProfileDao.getUsersProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getUserProfileFromNetwork(
        results: String, success: Consumer<ArrayList<UserProfile>>,
        error: Consumer<Throwable>
    ): Disposable = if (isNetworkAvailable()) {
        mServices.getUsersProfile(results).flatMapCompletable {
            return@flatMapCompletable if (it.results.size > 0) {
                mUserProfileDao.insertUsersProfile(it.results)
            } else
                Completable.error(Throwable(ShaadiApp.instance.getString(R.string.went_wrong)))
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Action {
                getUserProfileFromDb().subscribe(Consumer { userProfile ->
                    success.accept(userProfile as ArrayList<UserProfile>?)
                }, error)
            }, error)
    } else {
        noInternetAvailable(error)
        getDefaultDisposable()
    }

    fun updateProfileStatus(
        model: UserProfile,
        success: Consumer<Int>,
        error: Consumer<Throwable>
    ): Disposable {
        return mUserProfileDao.updateProfileStatus(model)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(success, error)
    }


}