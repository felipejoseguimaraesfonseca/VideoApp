package com.example.videoapp.data.repository

import android.content.Context
import com.example.videoapp.data.model.UserEntity
import java.lang.NumberFormatException

class UserRepository(context: Context) {

    private val mDatabase = AppDatabase.getDatabase(context).userDao()

    suspend fun saveUser(user: UserEntity): Boolean? {
        return try {
            val databaseSaveUserString = mDatabase.saveUser(user).toString()
            val databaseSaveUserInt = Integer.parseInt(databaseSaveUserString)
            databaseSaveUserInt > 0
        } catch (e: NumberFormatException) {
            null
        }
    }

    suspend fun getUser(email: String, password: String): UserEntity {
        return mDatabase.getUser(email, password)
    }

    suspend fun updateUser(user: UserEntity): Boolean {
        val mDatabaseInt = mDatabase.updateUser(user).toString().toInt()
        return mDatabaseInt > 0
    }

    suspend fun deleteUser(user: UserEntity): Boolean {
        return mDatabase.deleteUser(user).toString().toBoolean()
    }
}