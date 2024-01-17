package com.rizadwi.mandiri.android.lalulelang.util

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceUtil @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun getToken(): String {
        return sharedPreferences.getString(TOKEN_KEY, null) ?: ""
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    fun removeToken() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
    }

    companion object {
        const val TOKEN_KEY = "token"
    }
}