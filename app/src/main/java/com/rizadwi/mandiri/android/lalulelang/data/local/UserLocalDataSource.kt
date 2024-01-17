package com.rizadwi.mandiri.android.lalulelang.data.local

import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult

interface UserLocalDataSource {
    fun saveToken(token: String): ResourceResult<Unit>
    fun getToken(): ResourceResult<String>
    fun deleteToken(): ResourceResult<Unit>
}