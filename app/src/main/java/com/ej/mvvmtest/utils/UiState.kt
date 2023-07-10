package com.ej.mvvmtest.utils

import androidx.annotation.StringRes

sealed interface UiState<out T> {

    data class Success<T>(val data: T) : UiState<T>

    data class StringError(val errorText: String) : UiState<Nothing>

    data class StringResourceError(@StringRes val errorStringResource: Int) : UiState<Nothing>

    object Loading : UiState<Nothing>

}