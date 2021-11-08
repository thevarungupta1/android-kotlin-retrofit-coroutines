package com.thevarungupta.kotlin_coroutines_retrofit_demo.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.thevarungupta.kotlin_coroutines_retrofit_demo.data.repository.MainRepository
import com.thevarungupta.kotlin_coroutines_retrofit_demo.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository):ViewModel(){

    fun getPosts() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getPosts()))
        }catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred"))
        }
    }
}