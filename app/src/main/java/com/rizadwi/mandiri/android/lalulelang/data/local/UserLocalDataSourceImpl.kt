package com.rizadwi.mandiri.android.lalulelang.data.local

import com.rizadwi.mandiri.android.lalulelang.util.SharedPreferenceUtil
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(private val sharedPrefUtil: SharedPreferenceUtil) :
    UserLocalDataSource {
    override fun saveToken(token: String): ResourceResult<Unit> {
        sharedPrefUtil.saveToken(token)
        return ResourceResult.Success(Unit)
    }

    override fun getToken(): ResourceResult<String> {
        return ResourceResult.Success(sharedPrefUtil.getToken())
    }

    override fun deleteToken(): ResourceResult<Unit> {
        sharedPrefUtil.removeToken()
        return ResourceResult.Success(Unit)
    }
}