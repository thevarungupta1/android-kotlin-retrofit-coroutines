package com.thevarungupta.kotlin_coroutines_retrofit_demo.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thevarungupta.kotlin_coroutines_retrofit_demo.data.api.ApiHelper
import com.thevarungupta.kotlin_coroutines_retrofit_demo.data.repository.MainRepository
import com.thevarungupta.kotlin_coroutines_retrofit_demo.ui.main.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }


}