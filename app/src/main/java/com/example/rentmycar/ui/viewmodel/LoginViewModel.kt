package com.example.rentmycar.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmycar.data.api.request.UserRequest
import com.example.rentmycar.data.model.api.post.User
import com.example.rentmycar.data.model.login.LoginFormState
import com.example.rentmyuser.data.repositories.LoginRepository
import kotlinx.coroutines.launch


class LoginViewModel() : ViewModel() {
    private val repository = LoginRepository()

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<UserRequest>()
    val loginResult: LiveData<UserRequest> = _loginResult

    fun registerUser(register: User) {
        viewModelScope.launch {
            val response = repository.createUser(register)
//            _loginResult.postValue(response!!)
        }
    }
}