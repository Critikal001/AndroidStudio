package com.example.rentmycar.data.repositories


import android.content.Context
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.api.SimpleResponse
import com.example.rentmycar.data.model.api.post.*
import com.example.rentmycar.data.model.api.request.GetRental
import com.example.rentmycar.data.room.RoomService
import com.example.rentmycar.data.room.RoomService.context
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import retrofit2.Response


class RentalRepository {
        private fun client() = ServiceProvider.RentalApi

        fun getRentalList(): List<GetRental>? {
            val request =  client().retrofitService.getRentalList()
            return request.body()
        }

        suspend fun createRental(context: Context, rental: Rental) {
            val request = client().rentalClient.createRental(rental)
            if (!request.isSuccessful) {
                Toast.makeText(context, context.getString(R.string.no_database_connection), Toast.LENGTH_LONG).show()

            }
            Toast.makeText(RoomService.context, RoomService.context.getString(R.string.created), Toast.LENGTH_LONG).show()

        }

        suspend fun createCar(car: Car) {
            val request = client().rentalClient.createCar(car)
            if (!request.isSuccessful) {
                Toast.makeText(context, context.getString(R.string.no_database_connection), Toast.LENGTH_LONG).show()

            }
            Toast.makeText(context, context.getString(R.string.created), Toast.LENGTH_LONG).show()
        }

        suspend fun createEngine(engine:Engine) {
            val request = client().rentalClient.createEngine(engine)
            if (!request.isSuccessful) {
                Toast.makeText(context, context.getString(R.string.no_database_connection), Toast.LENGTH_LONG).show()

            }
            Toast.makeText(context, context.getString(R.string.created), Toast.LENGTH_LONG).show()
        }

        suspend fun createEngineSpec(engineSpec: EngineSpec) {
            val request = client().rentalClient.createEngineSpec(engineSpec)
            if (!request.isSuccessful) {
                Toast.makeText(context, context.getString(R.string.no_database_connection), Toast.LENGTH_LONG).show()
            }
            Toast.makeText(context, context.getString(R.string.created), Toast.LENGTH_LONG).show()
        }
        suspend fun createUser(user: User) {
            val request = client().rentalClient.createUser(user)
            if (!request.isSuccessful) {
                Toast.makeText(context, context.getString(R.string.no_database_connection), Toast.LENGTH_LONG).show()
            }
            Toast.makeText(context, context.getString(R.string.created), Toast.LENGTH_LONG).show()
        }

        suspend fun createProvider(provider: Provider) {
            val request = client().rentalClient.createProvider(provider)
            if (!request.isSuccessful) {
                Toast.makeText(context, context.getString(R.string.no_database_connection), Toast.LENGTH_LONG).show()

            }
            Toast.makeText(context, context.getString(R.string.created), Toast.LENGTH_LONG).show()
        }
        suspend fun createLocation(location: Location)  {
            val request = client().rentalClient.createLocation(location)
            if (request.failed || !request.isSuccessful) {
                Toast.makeText(context, context.getString(R.string.no_database_connection), Toast.LENGTH_LONG).show()

            }
            Toast.makeText(context, context.getString(R.string.created), Toast.LENGTH_LONG).show()
        }





}