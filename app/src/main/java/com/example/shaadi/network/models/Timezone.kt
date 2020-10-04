package com.example.shaadi.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Timezone(
    @SerializedName("offset") @Expose val offset: String,
    @SerializedName("description") @Expose val description: String
)