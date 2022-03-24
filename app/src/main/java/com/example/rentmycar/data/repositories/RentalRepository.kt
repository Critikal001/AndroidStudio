package com.example.rentmycar.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.icu.number.IntegerWidth
import android.util.Log
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.api.request.RentalRequest
import com.example.rentmycar.data.api.request.UserRequest
import com.example.rentmycar.data.model.api.post.Rental
import com.example.rentmycar.data.room.RentMyCarDatabase
import com.example.rentmycar.data.room.RentalRoom
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RentalRepository {
    companion object {
        private fun client() = ServiceProvider.RentalApi.rentalClient
        private fun api() = ServiceProvider.RentalApi.retrofitService
        private fun dao(context: Context) = RentMyCarDatabase.getInstance(context).rentalDao()

        suspend fun getRentalList(): List<RentalRequest>? {
            var rentalList: List<RentalRequest>? = null
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
                Toast.makeText(context,
                    context.getString(R.string.no_database_connection),
                    Toast.LENGTH_LONG).show()
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


        suspend fun createRental(rental: Rental, onResult: (Response<RentalRequest>?) -> Unit) {
            var call = api().createRental(rental)

            call.enqueue(object : Callback<RentalRequest> {
                override fun onResponse(
                    call: Call<RentalRequest>,
                    response: Response<RentalRequest>
                ) {


                    if (!response.isSuccessful) {

                        onResult(null)
                    }
                    onResult(response)
                }

                override fun onFailure(call: Call<RentalRequest>, t: Throwable) {
                    onResult(null)
                }
            })
        }

        fun getRentalById(rentalId: Integer, onResult: (Response<RentalRequest>?) -> Unit){
            val request = api().getRentalById(rentalId)

            request.enqueue(object : Callback<RentalRequest> {
                override fun onResponse(
                    call: Call<RentalRequest>,
                    response: Response<RentalRequest>
                ) {


                    if (!response.isSuccessful) {

                        onResult(null)
                    }
                    onResult(response)
                }

                override fun onFailure(call: Call<RentalRequest>, t: Throwable) {
                    onResult(null)
                }
            })
        }


    }

}