package com.infinixsoft.coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel : ViewModel() {
    fun getPokemon() = liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            try {
                emit(Resource.success(ApiClient.createService().getPokemon(0, 0)))
            } catch (e: Exception) {
                emit(Resource.error(null, e.message ?: "Error"))
            }
        }
}