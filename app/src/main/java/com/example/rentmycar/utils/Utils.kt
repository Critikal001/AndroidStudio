package com.example.rentmycar.utils

import android.content.Context
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.RunningRental

var idReservation:Int = 0
var userToken :String =""
val sharedPrefFile: String = "kotlinsharedpreference"
var idTokenUser :String = ""
var reservationsFiltred = MutableLiveData<List<RunningRental>>()
var reservations = MutableLiveData<List<RunningRental>>()
var valide: Int? = 2

class Utils(context: Context) {
    companion object {
        //méthode pour show and hide passwords
        fun showPassword(a: EditText, b: ImageView, isShow: Boolean) {
            if (isShow) {
                // To show the password
                a.transformationMethod = HideReturnsTransformationMethod.getInstance()
                b.setImageResource(R.drawable.ic_show_password)
            } else {
                // To hide the password
                a.transformationMethod = PasswordTransformationMethod.getInstance()
                b.setImageResource(R.drawable.ic_show_password)
            }
            // This line of code to put the pointer at the end of the password string
            a.setSelection(a.text.toString().length)
        }
    }
}