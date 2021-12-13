package com.example.videoapp.data.repository

import androidx.room.*
import com.example.videoapp.data.model.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(vararg userEntity: UserEntity): Long

    @Query("SELECT * from user_data WHERE email LIKE :email AND password LIKE :password")
    suspend fun getUser(email: String, password: String): List<UserEntity>

    @Update
    suspend fun updateUser(vararg userEntity: UserEntity)

    @Delete
    suspend fun deleteUser(vararg userEntity: UserEntity)

}