package com.example.videoapp.data.repository

import android.content.Context
import com.example.videoapp.data.model.UserEntity

class UserRepository(context: Context) {

    private val mDatabase = AppDatabase.getDatabase(context).userDao()

    suspend fun saveUser(user: UserEntity): Boolean? {
        return try {
            val databaseSaveUserLong = mDatabase.saveUser(user).toString().toLong()
            databaseSaveUserLong > 0
        } catch (e: NumberFormatException) {
            null
        }
    }

    suspend fun getUser(email: String, password: String): List<UserEntity> {
        return mDatabase.getUser(email, password)
    }

    suspend fun updateUser(user: UserEntity): Boolean {
        val mDatabaseLong = mDatabase.updateUser(user).toString().toLong()
        return mDatabaseLong > 0
    }

    suspend fun deleteUser(user: UserEntity): Boolean {
        return mDatabase.deleteUser(user).toString().toBoolean()
    }
}