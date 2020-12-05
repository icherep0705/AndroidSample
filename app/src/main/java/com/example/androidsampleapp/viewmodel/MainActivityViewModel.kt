package com.example.androidsampleapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.androidsampleapp.model.IDataSource
import com.example.androidsampleapp.model.PageDataSource
import com.example.androidsampleapp.service.Response

class MainActivityViewModel(context: Application): AndroidViewModel(context) {

    lateinit var dataSource: IDataSource

    fun getPage(): LiveData<Result<Response>> {
        dataSource = PageDataSource()
        return dataSource.getPage()
    }
}