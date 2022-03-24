package com.example.rentmycar.data.repositories

import android.content.Context
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.api.request.CarRequest
import com.example.rentmycar.data.api.request.RentalRequest
import com.example.rentmycar.data.model.api.post.Car
import com.example.rentmycar.data.model.api.post.Rental
import com.example.rentmycar.data.room.CarRoom
import com.example.rentmycar.data.room.RentMyCarDatabase
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CarRepository {
    companion object {
        private fun client() = ServiceProvider.RentalApi.rentalClient
        private fun api() = ServiceProvider.RentalApi.retrofitService
        private fun dao(context: Context) = RentMyCarDatabase.getInstance(context).carDao()

//    suspend fun getcarList(): List<CarRequest>? {
//        var carList : List<CarRequest>? = null
//        val request = client().getCar()
//        if (request!!.failed || !request.isSuccessful) {
//            return carList
//        }
//        if (request != null) {
//            carList = request.body
//        }
//
//        return carList
//    }

        suspend fun saveCar(context: Context, car: CarRoom): Long {
            return try {
                dao(context).createCar(car)
            } catch (e: Exception) {
                Toast.makeText(context,
                    context.getString(R.string.no_database_connection),
                    Toast.LENGTH_LONG).show()
                return 0
            }
        }

        suspend fun getCar(context: Context, carId: Int): CarRoom? {
            return try {
                dao(context).getCar(carId)
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    context.getString(R.string.no_database_connection),
                    Toast.LENGTH_LONG
                ).show()
                return null
            }
        }


        suspend fun createCar(car: Car, onResult: (Response<CarRequest>?) -> Unit) {
            var call = api().createCar(car)

            call.enqueue(object : Callback<CarRequest> {
                override fun onResponse(call: Call<CarRequest>, response: Response<CarRequest>) {


                    if (!response.isSuccessful) {

                        onResult(null)
                    }
                    onResult(response)
                }

                override fun onFailure(call: Call<CarRequest>, t: Throwable) {
                    onResult(null)
                }
            })
        }
    }
}
