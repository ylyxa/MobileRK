package com.example.mobilerk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class HostViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    private val _data = MutableLiveData<WebData>()
    val data: LiveData<WebData>
        get() = _data

    init {
        loadDataFromInternet()
    }

    private fun loadDataFromInternet() {
        viewModelScope.launch {
            try {
                val listResult = WebApi.retrofitService.getData("BTC", "USD", 10)
                _response.value = "Success"
                _data.value = listResult
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}