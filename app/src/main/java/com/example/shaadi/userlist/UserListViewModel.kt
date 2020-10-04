package com.example.shaadi.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shaadi.base.BaseViewModel
import com.example.shaadi.network.DataProvider
import com.example.shaadi.network.models.UserProfile
import io.reactivex.functions.Consumer

class UserListViewModel : BaseViewModel() {

    private val userProfileLiveData = MutableLiveData<ArrayList<UserProfile>>()
    val errorLiveData = MutableLiveData<String>()

    fun getUserProfile(): LiveData<ArrayList<UserProfile>> = userProfileLiveData

    fun fetchUserProfile() {
        dialogMessage.value = "Fetching Profiles..."
        dialogVisibility.value = true
        getDisposable().add(DataProvider.getUsersProfile("10", Consumer {
            dialogVisibility.value = false
            userProfileLiveData.value = it
        }, Consumer {
            dialogVisibility.value = false
            errorLiveData.value = it.message
        }))
    }

    public fun updateProfile(status: Int, model: UserProfile) {
        model.isProfileAccepted = status
        dialogMessage.value = "Updating Profile..."
        dialogVisibility.value = true
        getDisposable().add(DataProvider.updateProfileStatus(model, Consumer {
            fetchUserProfile()
        }, Consumer {
            dialogVisibility.value = false
            errorLiveData.value = it.message
        }))
    }

}