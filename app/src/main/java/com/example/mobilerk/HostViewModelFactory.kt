package com.example.mobilerk

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HostViewModelFactory(f: String, t: String, l: Int) : ViewModelProvider.Factory {
    private val limit: Int = l
    private val fsym: String = f
    private val tsym: String = t

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HostViewModel(fsym, tsym, limit) as T
    }
}