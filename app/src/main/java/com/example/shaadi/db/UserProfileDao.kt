package com.example.shaadi.db

import androidx.room.*
import com.example.shaadi.network.models.UserProfile
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsersProfile(usersProfile: List<UserProfile>): Completable

    @Query("SELECT * FROM user_profile_tbl")
    fun getUsersProfile(): Single<List<UserProfile>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateProfileStatus(model: UserProfile): Single<Int>
}