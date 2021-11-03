package com.example.allinone.page2.internet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allinone.page2.internet.network.MarsApi
import com.example.allinone.page2.internet.network.MarsPhoto
import kotlinx.coroutines.launch
import java.lang.Exception

enum class MarsApiStatus { LOADING, ERROR, DONE }

class InternetViewModel : ViewModel() {
    private val _state = MutableLiveData<MarsApiStatus>()
    val state: LiveData<MarsApiStatus> = _state

    private val _photo = MutableLiveData<List<MarsPhoto>>()
    val photo: LiveData<List<MarsPhoto>> = _photo

    init {
        get()
    }

    private fun get() {
        viewModelScope.launch {
            _state.value = MarsApiStatus.LOADING
            Log.d("!!!", "get: ")
            try {
                _photo.value = MarsApi.retrofitService.getPhotos()
                _state.value =MarsApiStatus.DONE
            } catch (e: Exception) {
                _state.value = MarsApiStatus.ERROR
                _photo.value = listOf()
            }
        }
    }
}