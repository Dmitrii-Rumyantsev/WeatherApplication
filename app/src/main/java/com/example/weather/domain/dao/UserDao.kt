package com.example.weather.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.weather.data.entity.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("UPDATE user SET location_now = :newLocation WHERE id = :userId")
    suspend fun updateLocationNow(userId: Int, newLocation: String)

    @Query("UPDATE user SET theme = :theme WHERE id = :userId")
    fun updateTheme(userId: Int, theme: Boolean)

    @Query("SELECT * FROM user WHERE id = 1")
    fun getUser(): User?

}

