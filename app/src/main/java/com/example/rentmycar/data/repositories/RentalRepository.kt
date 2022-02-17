package com.example.rentmycar.data.repositories

import android.content.Context
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.api.request.RentalRequest
import com.example.rentmycar.data.model.api.post.Rental
import com.example.rentmycar.data.room.RentMyCarDatabase
import com.example.rentmycar.data.room.RentalRoom
import com.example.rentmycar.ui.view.activity.HomeProviderActivity

class RentalRepository {
    private fun client() = ServiceProvider.RentalApi.rentalClient
    private fun dao(context : Context) = RentMyCarDatabase.getInstance(context).rentalDao()

    suspend fun getRentalList(): List<RentalRequest>? {
        var rentalList : List<RentalRequest>? = null
        val request = client().getRental()
        if (request!!.failed || !request.isSuccessful) {
            return rentalList
        }
        if (request != null) {
            rentalList = request.body
        }

        return rentalList
    }

    suspend fun saveRental(context: Context, rental: RentalRoom): Long {
        return try {
            dao(context).createRental(rental)
        } catch (e: Exception) {
            Toast.makeText(context, context.getString(R.string.no_database_connection), Toast.LENGTH_LONG).show()
            return 0
        }
    }

    suspend fun getRental(context: Context, rentalId: Int): RentalRoom? {
        return try {
            dao(context).getRental(rentalId)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                context.getString(R.string.no_database_connection),
                Toast.LENGTH_LONG
            ).show()
            return null
        }
    }



    suspend fun createRental(rental: Rental): RentalRequest? {
        var rentalRequest : RentalRequest? = null
        val request = client().createRental(rental)
        if (request!!.failed || !request.isSuccessful) {
            Toast.makeText(HomeProviderActivity.context, HomeProviderActivity.context.getString(R.string.error_put_rental), Toast.LENGTH_LONG).show()
            return null
        }
        if (request != null) {
            rentalRequest = request.body
        }
        return rentalRequest
    }





}