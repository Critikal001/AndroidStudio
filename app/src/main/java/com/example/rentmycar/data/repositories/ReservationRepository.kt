package com.example.rentmycar.data.repositories

import android.app.DownloadManager
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.model.api.post.Reservation
import com.example.rentmycar.data.model.api.post.RunningRental
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity


class ReservationRepository {
    private fun client() = ServiceProvider.RentalApi.retrofitService
//
//    suspend fun postReservation(reservation: Reservation): Reservation? {
//        val runningRental = RunningRental(
//            returnLocation = reservation.status.toString(),
//            drivingKm = reservation.price.toInt(),
//            rental = reservation.status,
//        )
//        val request = client().createReservation(runningRental)
//
//        if (request.failed || !request.isSuccessful) {
//            Toast.makeText(HomeCustomerActivity.context, HomeCustomerActivity.context.getString(R.string.error_post_reservation), Toast.LENGTH_LONG).show()
//            return null
//        }
//
//        return request
//
//    }
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