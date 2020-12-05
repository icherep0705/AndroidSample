package com.example.androidsampleapp.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsampleapp.R
import com.example.androidsampleapp.databinding.ActivityMainBinding
import com.example.androidsampleapp.viewmodel.MainActivityViewModel

/**
 *
 * MainActivity class.
 */
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[MainActivityViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUI()
        getPage()
    }

    /**
     * [initUI] is used to initialize recyclerView, set layoutManager and adapter
     */
    private fun initUI() {
        binding.pageContainer.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PageAdapter()
            addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))
        }
    }

    /**
     * [getPage] is used to get data from dataSource
     */
    private fun getPage() {
        Log.d(TAG, "getPage()")

        viewModel.getPage().observe(this, { result ->
            result.fold(
                onSuccess = { response ->
                    response.page?.cards?.let {
                        (binding.pageContainer.adapter as? PageAdapter)?.cardList = it
                    }
                    Log.d(TAG, "getPage() - isSuccess: ${result.isSuccess}")
                },
                onFailure = {
                    Log.d(TAG, "getPage() - onFailure: ${result.isFailure}")
                    Log.d(TAG, "getPage() - ${it.message}")
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            )
        })

    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}


