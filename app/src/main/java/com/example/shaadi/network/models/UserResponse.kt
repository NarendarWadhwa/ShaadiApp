package com.example.shaadi.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("results") @Expose val results: ArrayList<UserProfile>,
    @SerializedName("info") @Expose val info: Info
)