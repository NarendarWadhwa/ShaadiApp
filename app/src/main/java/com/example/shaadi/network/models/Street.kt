package com.example.shaadi.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Street(
    @SerializedName("number") @Expose val number: Long,
    @SerializedName("name") @Expose val name: String
)