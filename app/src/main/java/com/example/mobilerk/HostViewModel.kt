package com.example.mobilerk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class HostViewModel(f: String, t: String, l: Int) : ViewModel() {
    private val _response = MutableLiveData<String>()
    private val _data = MutableLiveData<WebData>()
    val data: LiveData<WebData>
        get() = _data

    val response: LiveData<String>
        get() = _response

    init {
        loadDataFromInternet(f, t, l)
    }

    private fun loadDataFromInternet(fsym: String, tsym: String, limit: Int) {
        viewModelScope.launch {
            try {
                val listResult = WebApi.retrofitService.getData(fsym, tsym, limit)
                _response.value = "Success"
                _data.value = listResult
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}