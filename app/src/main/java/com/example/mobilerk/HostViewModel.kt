package com.example.mobilerk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilerk.WebApi
import com.example.mobilerk.WebData
import kotlinx.coroutines.launch
import java.lang.Exception

class HostViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    private val _data = MutableLiveData<List<WebData>>()
    val response: LiveData<String>
        get() = _response
    val data: LiveData<List<WebData>>
        get() = _data

    init {
        loadDataFromInternet()
    }

    private fun loadDataFromInternet() {
        viewModelScope.launch {
            try {
                val listResult = WebApi.retrofitService.getData("albums")
                _response.value = "Success: ${listResult.size} data retrieved"
                _data.value = listResult
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}