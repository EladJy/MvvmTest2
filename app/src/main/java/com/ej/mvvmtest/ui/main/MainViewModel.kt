package com.ej.mvvmtest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ej.mvvmtest.R
import com.ej.mvvmtest.data.MainRepository
import com.ej.mvvmtest.data.models.Item
import com.ej.mvvmtest.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _items = MutableLiveData<UiState<List<Item>>>(UiState.StringResourceError(errorStringResource = R.string.no_videos))
    val items: LiveData<UiState<List<Item>>> = _items
    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        _items.postValue(UiState.StringError(throwable.message.toString()))
    }

    fun getVideosByQuery(query: String) {
        _items.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            val response = repository.getVideosByQuery(query)
            withContext(Dispatchers.Main) {
                if(response.items.isEmpty()) {
                    _items.value = UiState.StringResourceError(errorStringResource = R.string.no_videos)
                } else {
                    _items.value = UiState.Success(response.items)
                }
            }
        }
    }
}