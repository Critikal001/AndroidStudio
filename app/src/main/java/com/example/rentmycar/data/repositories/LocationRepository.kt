package com.example.rentmycar.data.repositories

import android.content.Context
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.api.request.EngineSpecRequest
import com.example.rentmycar.data.api.request.LocationRequest
import com.example.rentmycar.data.model.api.post.EngineSpec
import com.example.rentmycar.data.model.api.post.Location
import com.example.rentmycar.data.room.LocationRoom
import com.example.rentmycar.data.room.RentMyCarDatabase
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LocationRepository {
    companion object {
        private fun client() = ServiceProvider.RentalApi.rentalClient
        private fun dao(context: Context) = RentMyCarDatabase.getInstance(context).locationDao()
        private fun api() = ServiceProvider.RentalApi.retrofitService


        suspend fun getLocationList(): List<LocationRequest>? {
            var locationList: List<LocationRequest>? = null
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
                Toast.makeText(context,
                    context.getString(R.string.no_database_connection),
                    Toast.LENGTH_LONG).show()
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


        suspend fun createLocation(
            location: Location,
            onResult: (Response<LocationRequest>?) -> Unit
        ) {
            var call = api().createLocation(location)

            call.enqueue(object : Callback<LocationRequest> {
                override fun onResponse(
                    call: Call<LocationRequest>,
                    response: Response<LocationRequest>
                ) {


                    if (!response.isSuccessful) {

                        onResult(null)
                    }
                    onResult(response)
                }

                override fun onFailure(call: Call<LocationRequest>, t: Throwable) {
                    onResult(null)
                }
            })
        }
    }
}