package com.example.rentmycar.data.repositories

import android.app.DownloadManager
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
    companion object
    {
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
//
//    suspend fun getTimeslotsByReservation(reservationNumber: String): List<GetAvailabilityResponse> {
//        val request = client().getTimeslotsByReservation(reservationNumber)
//
//        if (request.failed || !request.isSuccessful) {
//            return emptyList()
//        }
//        return request.body
//    }
//
//    suspend fun getReservation(reservationNumber: String): Reservation? {
//        val request = client().getReservation(reservationNumber)
//
//        if (request.failed || !request.isSuccessful) {
//            Toast.makeText(RentMyCarApplication.context,
//                RentMyCarApplication.context.getString(R.string.error_get_reservation), Toast.LENGTH_LONG).show()
//            return null
//        }
//
//        return ReservationMapper.buildFrom(
//            response = request.body,
//            product = request.body.product,
//            availability = getTimeslotsByReservation(request.body.reservationNumber))
//    }
//
//    suspend fun getReservationList(status: String?): List<Reservation?> {
//        val reservationList = mutableListOf<Reservation>()
//        val request = client().getReservationList(status)
//
//        if (request.failed || !request.isSuccessful) {
//            return emptyList()
//        }
//
//        request.body.forEach { item ->
//
//            val availability = getTimeslotsByReservation(item.reservationNumber)
//
//            val reservation: Reservation = ReservationMapper.buildFrom(
//                response = item,
//                product = item.product,
//                availability = availability
//            )
//
//            reservationList.add(reservation)
//        }
//        return reservationList
//    }
    }

}