package com.example.videoapp.data.repository

import androidx.room.*
import com.example.videoapp.data.model.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun saveUser(userEntity: UserEntity)

    @Query("SELECT * from user_data WHERE email = :email and password = :password")
    suspend fun getUser(email: String, password: String): UserEntity

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Delete
    suspend fun deleteUser(userEntity: UserEntity)

}