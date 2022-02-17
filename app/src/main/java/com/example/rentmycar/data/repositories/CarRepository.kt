package com.example.rentmycar.data.repositories

import android.content.Context
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.api.request.CarRequest
import com.example.rentmycar.data.model.api.post.Car
import com.example.rentmycar.data.room.CarRoom
import com.example.rentmycar.data.room.RentMyCarDatabase
import com.example.rentmycar.ui.view.activity.HomeProviderActivity


class CarRepository {
    private fun client() = ServiceProvider.RentalApi.rentalClient
    private fun dao(context : Context) = RentMyCarDatabase.getInstance(context).carDao()

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
            Toast.makeText(context, context.getString(R.string.no_database_connection), Toast.LENGTH_LONG).show()
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



    suspend fun createCar(car: Car): CarRequest? {
        var carRequest : CarRequest? = null
        val request = client().createCar(car)
        if (request!!.failed || !request.isSuccessful) {
            Toast.makeText(HomeProviderActivity.context, HomeProviderActivity.context.getString(R.string.error_put_car), Toast.LENGTH_LONG).show()
            return null
        }
        if (request != null) {
            carRequest = request.body
        }
        return carRequest
    }
}