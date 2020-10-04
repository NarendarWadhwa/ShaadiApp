package com.example.shaadi.db

import androidx.room.TypeConverter
import com.example.shaadi.network.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromName(name: Name) = gson.toJson(name)

    @TypeConverter
    fun toName(string: String): Name {
        val type = object : TypeToken<Name>() {}.type
        return gson.fromJson(string, type)
    }

    @TypeConverter
    fun fromLocation(location: Location) = gson.toJson(location)

    @TypeConverter
    fun toLocation(string: String): Location {
        val type = object : TypeToken<Location>() {}.type
        return gson.fromJson(string, type)
    }

    @TypeConverter
    fun fromLogin(login: Login) = gson.toJson(login)

    @TypeConverter
    fun toLogin(string: String): Login {
        val type = object : TypeToken<Login>() {}.type
        return gson.fromJson(string, type)
    }

    @TypeConverter
    fun fromDob(dob: Dob) = gson.toJson(dob)

    @TypeConverter
    fun toDob(string: String): Dob {
        val type = object : TypeToken<Dob>() {}.type
        return gson.fromJson<Dob>(string, type)
    }

    @TypeConverter
    fun fromRegistered(registered: Registered) = gson.toJson(registered)

    @TypeConverter
    fun toRegistered(string: String): Registered {
        val type = object : TypeToken<Registered>() {}.type
        return gson.fromJson(string, type)
    }

    @TypeConverter
    fun fromId(id: Id) = gson.toJson(id)

    @TypeConverter
    fun toId(string: String): Id {
        val type = object : TypeToken<Id>() {}.type
        return gson.fromJson(string, type)
    }

    @TypeConverter
    fun fromPicture(picture: Picture) = gson.toJson(picture)

    @TypeConverter
    fun toPicture(string: String): Picture {
        val type = object : TypeToken<Picture>() {}.type
        return gson.fromJson(string, type)
    }


}