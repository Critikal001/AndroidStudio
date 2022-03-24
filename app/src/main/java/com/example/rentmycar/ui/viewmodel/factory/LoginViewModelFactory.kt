package com.example.rentmycar.ui.viewmodel.factory

import androidx.lifecycle.ViewModelProvider

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
abstract class LoginViewModelFactory : ViewModelProvider.Factory {

//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
//            return LoginViewModel(
//                loginRepository = LoginRepository(
//                )
//            ) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
}