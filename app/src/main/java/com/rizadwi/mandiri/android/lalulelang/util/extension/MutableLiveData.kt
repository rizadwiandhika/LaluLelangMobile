package com.rizadwi.mandiri.android.lalulelang.util.extension

import androidx.lifecycle.MutableLiveData
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState.Loading

fun <T : Any> MutableLiveData<UIState<T>>.postLoading() {
    this.postValue(Loading)
}

fun <T : Any> MutableLiveData<UIState<T>>.postSuccess(data: T) {
    this.postValue(UIState.Success(data))
}

fun <T : Any> MutableLiveData<UIState<T>>.postError(err: Error) {
    this.postValue(UIState.Error(err))
}