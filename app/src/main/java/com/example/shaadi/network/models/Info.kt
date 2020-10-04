package com.example.shaadi.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("results") @Expose val results: Int
)