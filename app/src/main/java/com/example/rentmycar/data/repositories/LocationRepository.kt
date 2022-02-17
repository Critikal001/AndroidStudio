package com.example.rentmycar.data.repositories

import android.content.Context
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.api.request.LocationRequest
import com.example.rentmycar.data.model.api.post.Location
import com.example.rentmycar.data.room.LocationRoom
import com.example.rentmycar.data.room.RentMyCarDatabase
import com.example.rentmycar.ui.view.activity.HomeProviderActivity


class LocationRepository {
    private fun client() = ServiceProvider.RentalApi.rentalClient
    private fun dao(context : Context) = RentMyCarDatabase.getInstance(context).locationDao()

    suspend fun getLocationList(): List<LocationRequest>? {
        var locationList : List<LocationRequest>? = null
        val request = client().getLocation()
        if (request!!.failed || !request.isSuccessful) {
            return locationList
        }
        if (request != null) {
            locationList = request.body
        }

        return locationList
    }

    suspend fun saveLocation(context: Context, location: LocationRoom): Long {
        return try {
            dao(context).createLocation(location)
        } catch (e: Exception) {
            Toast.makeText(context, context.getString(R.string.no_database_connection), Toast.LENGTH_LONG).show()
            return 0
        }
    }

    suspend fun getLocation(context: Context, locationId: Int): LocationRoom? {
        return try {
            dao(context).getLocation(locationId)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                context.getString(R.string.no_database_connection),
                Toast.LENGTH_LONG
            ).show()
            return null
        }
    }



    suspend fun createLocation(location: Location): LocationRequest? {
        var locationRequest : LocationRequest? = null
        val request = client().createLocation(location)
        if (request!!.failed || !request.isSuccessful) {
            Toast.makeText(HomeProviderActivity.context, HomeProviderActivity.context.getString(R.string.error_put_location), Toast.LENGTH_LONG).show()
            return null
        }
        if (request != null) {
            locationRequest = request.body
        }
        return locationRequest
    }
}