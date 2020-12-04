package com.example.androidsampleapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.androidsampleapp.service.ApiClient
import com.example.androidsampleapp.service.BackendApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MainActivityViewModel(context: Application): AndroidViewModel(context) {

    private val mCoroutineContext = Dispatchers.IO + SupervisorJob()
    private val mPurchaseAPI = ApiClient.getClient().create(BackendApi::class.java)

    fun getPage() = liveData(mCoroutineContext) {
        emit(
                kotlin.runCatching {
                    mPurchaseAPI.getPage()
                })
    }
}