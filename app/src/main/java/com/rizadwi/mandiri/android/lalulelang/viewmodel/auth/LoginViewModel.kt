package com.rizadwi.mandiri.android.lalulelang.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.LoginRequest
import com.rizadwi.mandiri.android.lalulelang.usecase.auth.CheckIfAuthenticatedUseCase
import com.rizadwi.mandiri.android.lalulelang.usecase.auth.LoginUseCase
import com.rizadwi.mandiri.android.lalulelang.usecase.auth.LogoutUseCase
import com.rizadwi.mandiri.android.lalulelang.usecase.auth.SaveTokenUseCase
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState
import com.rizadwi.mandiri.android.lalulelang.util.extension.postError
import com.rizadwi.mandiri.android.lalulelang.util.extension.postLoading
import com.rizadwi.mandiri.android.lalulelang.util.extension.postSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val checkIfAuthenticatedUseCase: CheckIfAuthenticatedUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _logoutAction: MutableLiveData<UIState<Unit>> = MutableLiveData()
    private val _isAuthenticated: MutableLiveData<Boolean> = MutableLiveData()
    private val _loginRequest: MutableLiveData<UIState<Unit>> = MutableLiveData()

    val logoutAction: LiveData<UIState<Unit>> get() = _logoutAction
    val isAuthenticated: LiveData<Boolean> get() = _isAuthenticated
    val loginRequest: LiveData<UIState<Unit>> get() = _loginRequest

    fun authenticate(email: String, password: String) = viewModelScope.launch {
        _loginRequest.postLoading()

        when (val result = loginUseCase.invoke(LoginRequest(email, password))) {
            is ResourceResult.Failure -> _loginRequest.postError(result.cause)
            is ResourceResult.Success -> handleLoginSuccess(result.payload.data!!.token)
        }

    }

    fun logout() = viewModelScope.launch {
        when (val result = logoutUseCase.invoke()) {
            is ResourceResult.Failure -> _logoutAction.postError(result.cause)
            is ResourceResult.Success -> _logoutAction.postSuccess(Unit)
        }
    }

    fun checkIfAuthenticated() = viewModelScope.launch {
        val result = checkIfAuthenticatedUseCase.invoke()
        _isAuthenticated.postValue(result)
    }

    private fun handleLoginSuccess(token: String) = viewModelScope.launch {
        when (val result = saveTokenUseCase.invoke(token)) {
            is ResourceResult.Failure -> _loginRequest.postError(result.cause)
            is ResourceResult.Success -> _loginRequest.postSuccess(Unit)
        }
    }
}