package com.example.videoapp.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.videoapp.R
import com.example.videoapp.data.model.UserEntity
import com.example.videoapp.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel constructor(application: Application) : AndroidViewModel(application) {

    private lateinit var mUserRepository: UserRepository

    private val mSaveUser = MutableLiveData<Boolean>()
    private val mUpdateUser = MutableLiveData<Boolean>()
    private val mDeleteUser = MutableLiveData<Boolean>()

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    companion object {
        private val TAG = UserViewModel::class.java.simpleName
    }

    fun saveUser(userId: Long, firstName: String, lastName: String, email: String, password: String) {
        try {
            if (hasInternetConnection()) {
                viewModelScope.launch(Dispatchers.IO) {
                    val user = UserEntity().apply {
                        this.userId = userId
                        this.firstName = firstName
                        this.lastName = lastName
                        this.email = email
                        this.password = password
                    }
                    try {
                        if (userId.toString().toInt() == 0) {
                            mSaveUser.postValue(mUserRepository.saveUser(user))
                            _messageEventData.postValue(R.string.account_saved_successfully)
                        } else {
                            mSaveUser.postValue(mUserRepository.updateUser(user))
                            _messageEventData.postValue(R.string.account_updated_successfully)
                        }
                    } catch (exception: Exception) {
                        _messageEventData.postValue(R.string.account_saved_failure)
                        Log.e(TAG, exception.toString())
                    }
                }
            }
        } catch (exception: Exception) {
            _messageEventData.postValue(R.string.no_internet_connection)
            Log.e(TAG, exception.toString())
        }
    }

    fun getUser(email: String, password: String) {
        try {
            if (hasInternetConnection()) {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        mUserRepository.getUser(email, password)
                        _messageEventData.postValue(R.string.account_logged_successfully)

                    } catch (exception: Exception) {
                        _messageEventData.postValue(R.string.account_logged_failure)
                        Log.e(TAG, exception.toString())
                    }
                }
            }
        } catch (exception: Exception) {
            _messageEventData.postValue(R.string.no_internet_connection)
            Log.e(TAG, exception.toString())
        }
    }

    fun updateUser(userId: Long, firstName: String, lastName: String, email: String, password: String) {
        try {
            if (hasInternetConnection()) {
                viewModelScope.launch(Dispatchers.IO) {
                    val user = UserEntity().apply {
                        this.userId = userId
                        this.firstName = firstName
                        this.lastName = lastName
                        this.email = email
                        this.password = password
                    }
                    try {
                        if (userId > 0) {
                            mUpdateUser.postValue(mUserRepository.updateUser(user))
                            _messageEventData.postValue(R.string.account_updated_successfully)
                        }
                    } catch (exception: Exception) {
                        _messageEventData.postValue(R.string.account_updated_failure)
                        Log.e(TAG, exception.toString())
                    }
                }
            }
        } catch (exception: Exception) {
            _messageEventData.postValue(R.string.no_internet_connection)
            Log.e(TAG, exception.toString())
        }
    }

    fun deleteUser(userId: Long, firstName: String, lastName: String, email: String, password: String) {
        try {
            if (hasInternetConnection()) {
                viewModelScope.launch(Dispatchers.IO) {
                    val user = UserEntity().apply {
                        this.userId = userId
                        this.firstName = firstName
                        this.lastName = lastName
                        this.email = email
                        this.password = password
                    }
                    try {
                        if (userId > 0) {
                            mDeleteUser.postValue(mUserRepository.deleteUser(user))
                            _messageEventData.postValue(R.string.account_deleted_successfully)
                        }
                    } catch (exception: Exception) {
                        _messageEventData.postValue(R.string.account_deleted_failure)
                        Log.e(TAG, exception.toString())
                    }
                }
            }
        } catch (exception: Exception) {
            _messageEventData.postValue(R.string.no_internet_connection)
            Log.e(TAG, exception.toString())
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}
