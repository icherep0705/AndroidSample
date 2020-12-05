package com.example.androidsampleapp.model

import androidx.lifecycle.LiveData
import com.example.androidsampleapp.service.Response

interface IDataSource {
    fun getPage(): LiveData<Result<Response>>
}