package com.example.rentmycar.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.rentmycar.data.api.ServiceProvider

import com.example.rentmycar.data.model.api.post.User



import com.example.rentmycar.sharedPrefFile
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.example.rentmycar.ui.view.activity.LoginActivity
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository {
    private fun client() = ServiceProvider.RentalApi.retrofitService





    fun login(
        context: Context,
        email: String,
        password: String
    ) {


        val login = client().getUser(email) // consommation de l'api

        val sharedPref = context.getSharedPreferences(
            sharedPrefFile, Context.MODE_PRIVATE
        )

        login.enqueue(object : Callback<User> {

            @SuppressLint("RestrictedApi")
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (!response.isSuccessful()) {
                    val gson = Gson()
                    val message: User = gson.fromJson(
                        response.errorBody()!!.charStream(),
                        User::class.java
                    )
                    Toast.makeText(context, "Gebruikersnaam niet gevonden", Toast.LENGTH_LONG)
                        .show()

                } else {
                    val resp = response.body()

                    if (resp != null) {
                        if (resp.passWord == password) {

                            with(sharedPref?.edit()) {
                                this?.putString("userID", resp.userId.toString())
                                this?.putString("userRole", resp.userRole.toString())
                                this?.putString("token", resp.passWord)
                                this?.putBoolean("connected", true)
                                this?.apply()
                            }

                            Toast.makeText(context, "Connexion établie", Toast.LENGTH_SHORT).show()
                            if (resp.userRole == 1) {
                                val myIntent = Intent(context, HomeProviderActivity::class.java)
                                context.startActivity(myIntent)
                            }
                            if (resp.userRole == 2) {
                                val myIntent = Intent(context, HomeCustomerActivity::class.java)
                                context.startActivity(myIntent)
                            }
                        }
                    }
                }


            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun register(
        context: Context,
        user: com.example.rentmycar.data.model.api.post.User
    ) {
        val userResponse = client().getUser(user.userName)
            userResponse.enqueue(object : Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val request  = client().createUser(user)

                    val sharedPref = context.getSharedPreferences(
                        sharedPrefFile, Context.MODE_PRIVATE
                    )
                    request.enqueue(object : Callback<User> {
                        override fun onResponse(
                            call: Call<User>,
                            response: Response<User>
                        ) {
                            if (!response.isSuccessful()) {
                                val gson = Gson()
                                val message: User = gson.fromJson(
                                    response.errorBody()!!.charStream(),
                                    User::class.java
                                )
                                Toast.makeText(
                                    context,
                                    "Error",
                                    Toast.LENGTH_LONG
                                ).show();


                            } else {
                                val resp = response.body()
                                Toast.makeText(
                                    context,
                                    "Succesfull",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val userID = resp?.userId
                                with(sharedPref?.edit()) {
                                    this?.putString("userID", userID.toString())
                                    this?.apply()
                                }
                                Toast.makeText(context, "Votre compte a été crée avec succées", Toast.LENGTH_SHORT).show()

                                val myIntent = Intent(context, LoginActivity::class.java)
                                context.startActivity(myIntent)
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            Toast.makeText(
                                context,
                                "Un erreur cest produit",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Un erreur cest produit",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })



    }
}




