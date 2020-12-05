package com.example.androidsampleapp.model

import androidx.lifecycle.liveData
import com.example.androidsampleapp.service.ApiClient
import com.example.androidsampleapp.service.BackendApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 *
 * PageDataSource interface
 */
class PageDataSource: IDataSource {

    private val mCoroutineContext = Dispatchers.IO + SupervisorJob()
    private val mPurchaseAPI = ApiClient.getClient().create(BackendApi::class.java)

    override fun getPage() = liveData(mCoroutineContext) {
        emit(
            kotlin.runCatching {
                mPurchaseAPI.getPage()
            })
    }
}