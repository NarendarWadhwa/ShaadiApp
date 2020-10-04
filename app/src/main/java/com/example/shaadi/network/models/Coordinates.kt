package com.example.shaadi.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Coordinates(
    @SerializedName("latitude") @Expose val latitude: Double,
    @SerializedName("longitude") @Expose val longitude: Double
)