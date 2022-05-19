package com.example.rentmycar.data.repositories

import android.app.DownloadManager
import android.content.Context
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.model.api.post.Rental
import com.example.rentmycar.data.model.api.post.Reservation

import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReservationRepository {
    companion object {
        private fun client() = ServiceProvider.RentalApi.retrofitService

        fun postReservation(reservation: Reservation, onResult: (Response<Reservation>?) -> Unit) {
            var call = client().createReservation(reservation)

            call.enqueue(object : Callback<Reservation> {
                override fun onResponse(
                    call: Call<Reservation>,
                    response: Response<Reservation>,
                ) {


                    if (!response.isSuccessful) {

                        onResult(null)
                    }
                    onResult(response)
                }

                override fun onFailure(call: Call<Reservation>, t: Throwable) {
                    onResult(null)
                }
            })

        }


        fun getReservationList(

            onResult: (Response<List<Reservation>>?) -> Unit
        ) {

            val call = client().getReservationList()
            call.enqueue(object : Callback<List<Reservation>> {
                override fun onResponse(
                    call: Call<List<Reservation>>,
                    response: Response<List<Reservation>>,
                ) {


                    if (!response.isSuccessful) {

                        onResult(null)
                    }
                    onResult(response)
                }

                override fun onFailure(call: Call<List<Reservation>>, t: Throwable) {
                    onResult(null)
                }
            })
        }

    }

}