package com.example.videoapp.data.repository

import android.content.Context
import com.example.videoapp.data.model.UserEntity

class UserRepository(context: Context) {

    private val mDatabase = AppDatabase.getDatabase(context).userDao()

    suspend fun saveUser(user: UserEntity) {
       mDatabase.saveUser(user)
    }

    suspend fun getUser(email: String, password: String): List<UserEntity> {
        return mDatabase.getUser(email, password)
    }

    suspend fun updateUser(user: UserEntity) {
        mDatabase.updateUser(user)
    }

    suspend fun deleteUser(user: UserEntity) {
        mDatabase.deleteUser(user)
    }
}