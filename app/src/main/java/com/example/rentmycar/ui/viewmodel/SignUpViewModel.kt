package com.example.rentmycar.ui.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.model.api.EngineSpec
import kotlinx.coroutines.launch


class SignUpViewModel : ViewModel() {
    private val _todoResponse: MutableLiveData<String> = MutableLiveData()

    val todoResponse: LiveData<String> // todo: voor demo String, later List<TodoItem>
        get() = _todoResponse


}