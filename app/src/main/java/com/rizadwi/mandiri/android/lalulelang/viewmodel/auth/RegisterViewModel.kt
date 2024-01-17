package com.rizadwi.mandiri.android.lalulelang.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserRequest
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.base.Success
import com.rizadwi.mandiri.android.lalulelang.usecase.auth.RegisterUserUseCase
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState
import com.rizadwi.mandiri.android.lalulelang.util.extension.postError
import com.rizadwi.mandiri.android.lalulelang.util.extension.postLoading
import com.rizadwi.mandiri.android.lalulelang.util.extension.postSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUserUseCase: RegisterUserUseCase) :
    ViewModel() {
    private val _registerRequest: MutableLiveData<UIState<Success<RegisterUserResponse>>> =
        MutableLiveData()
    val registerRequest: LiveData<UIState<Success<RegisterUserResponse>>>
        get() = _registerRequest

    fun register(name: String, email: String, password: String, retypedPassword: String) =
        viewModelScope.launch {
            _registerRequest.postLoading()

            if (password != retypedPassword) {
                _registerRequest.postError(Error("Password must be the same"))
                return@launch
            }

            val request = RegisterUserRequest(name, email, password)
            when (val result = registerUserUseCase.invoke(request)) {
                is ResourceResult.Failure -> _registerRequest.postError(result.cause)
                is ResourceResult.Success -> _registerRequest.postSuccess(result.payload)
            }
        }
}