package com.example.shaadi.network.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user_profile_tbl")
data class UserProfile(
    @PrimaryKey(autoGenerate = true) val _id: Int,
    @SerializedName("gender") @Expose val gender: String,
    @SerializedName("name") @Expose val name: Name,
    @SerializedName("location") @Expose val location: Location,
    @SerializedName("email") @Expose val email: String,
    @SerializedName("login") @Expose val login: Login,
    @SerializedName("dob") @Expose val dob: Dob,
    @SerializedName("registered") @Expose val registered: Registered,
    @SerializedName("phone") @Expose val phone: String,
    @SerializedName("cell") @Expose val cell: String,
    @SerializedName("id") @Expose val userId: Id,
    @SerializedName("picture") @Expose val picture: Picture,
    @SerializedName("nat") @Expose val nat: String,
    @ColumnInfo(name = "is_profile_accepted")
    var isProfileAccepted: Int
)