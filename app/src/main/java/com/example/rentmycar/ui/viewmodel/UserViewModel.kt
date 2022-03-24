package com.example.rentmycar.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.rentmycar.data.model.api.post.User

import com.example.rentmycar.data.repositories.UserRepository
import com.example.rentmycar.data.room.RentMyCarDatabase

import com.example.rentmycar.ui.view.activity.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository = UserRepository()



    fun addUser(context: Context, user: com.example.rentmycar.data.model.api.post.User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.register(context, user)

        }
    }



    fun userLogin(context: Context, userName: String, userPassword : String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(context, userName, userPassword)

        }
    }
}