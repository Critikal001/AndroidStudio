package com.example.rentmycar.ui.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.model.api.EngineSpec
import kotlinx.coroutines.launch

class RentalViewModel : ViewModel() {
    private val _todoResponse: MutableLiveData<String> = MutableLiveData()

    val todoResponse: LiveData<String> // todo: voor demo String, later List<TodoItem>
        get() = _todoResponse

    fun createEngineSpec(engineSpec: EngineSpec) {
        viewModelScope.launch {
            try {
                ServiceProvider.TodoApi.retrofitService.createEngineSpec(engineSpec)
                Log.i(TAG, "postTodoItem: ${engineSpec} posted")
                _todoResponse.value = "postTodoItem: ${engineSpec} posted"
            } catch (e: Exception) {
                _todoResponse.value = e.message.toString()
            }
        }
    }

    fun getEngineSpec(){
        ServiceProvider.TodoApi.retrofitService.getEngineSpec()
    }
}