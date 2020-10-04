package com.example.shaadi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shaadi.network.models.UserProfile

@Database(entities = [UserProfile::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ShaadiDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: ShaadiDatabase? = null
        fun getDb(context: Context): ShaadiDatabase? {
            if (INSTANCE == null) {
                synchronized(ShaadiDatabase) {
                    INSTANCE = Room.databaseBuilder(
                        context, ShaadiDatabase::class.java, "shaadi_db.db"
                    ).build()
                }
            }
            return INSTANCE
        }

    }

    abstract fun userProfileDao(): UserProfileDao
}