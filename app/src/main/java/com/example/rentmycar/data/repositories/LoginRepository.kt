package com.example.rentmyuser.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.api.request.UserRequest
import com.example.rentmycar.data.model.api.post.User
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.example.rentmycar.ui.view.activity.LoginActivity
import com.example.rentmycar.utils.sharedPrefFile
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository {
    private fun client() = ServiceProvider.RentalApi.rentalClient
    

//    suspend fun getuserList(): List<UserRequest>? {
//        var userList : List<UserRequest>? = null
//        val request = client().getUser()
//        if (request!!.failed || !request.isSuccessful) {
//            return userList
//        }
//        if (request != null) {
//            userList = request.body
//        }
//
//        return userList
//    }


    fun login(
        context: Context,
        userName: String,
        password: String
    ) {
        val request = client().getUser(userName) // consommation de l'api

        val sharedPref = context.getSharedPreferences(
            sharedPrefFile, Context.MODE_PRIVATE
        )

        if (request == null){
            Toast.makeText(context, "Gebruiker niet gevonden", Toast.LENGTH_LONG).show()
        } else {
            if (request.body.passWord == password)
                with(sharedPref?.edit()) {
                    this?.putString("userID", request.body.userId)
                    this?.putInt("userRole", request.body.userRole)
                    this?.putBoolean("connected", true)
                    this?.apply()
                    if (request.body.userRole == 1) {
                        val intent = Intent(context, HomeProviderActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        val intent = Intent(context, HomeCustomerActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            }
        }





     fun createUser(user: User): UserRequest? {
        var userRequest : UserRequest? = null
        val request = client().createUser(user)
        if (request!!.failed || !request.isSuccessful) {
            Toast.makeText(LoginActivity.context, LoginActivity.context.getString(R.string.error_put_user), Toast.LENGTH_LONG).show()
            return null
        }
        if (request != null) {
            userRequest = request.body
        }
        return userRequest
    }
}

