package com.example.rentmycar.data.repositories

import android.content.Context
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.model.api.post.Car
import com.example.rentmycar.data.room.RentMyCarDatabase
import com.example.rentmycar.data.room.RentalPlan
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RentalPlanRepository {
    companion object{
        fun dao(context: Context) = RentMyCarDatabase.getInstance(context).RentalPlanDao()
        fun client() = ServiceProvider.RentalApi.retrofitService

        suspend fun getRentalPlanById(context: Context, id: Int): RentalPlan? {

            return dao(context).getRentalPlan(id)
        }

        suspend fun postRentalPlan(context: Context, rentalPlan: RentalPlan): Int? {
            return dao(context).createRentalPlan(rentalPlan).toInt()
        }

        fun createRentalPlan(rentalPlan: com.example.rentmycar.data.model.api.post.RentalPlan, onResult: (Response<com.example.rentmycar.data.model.api.post.RentalPlan>?) -> Unit){
            val request = client().createRentalPlan(rentalPlan = rentalPlan)

            request.enqueue(object : Callback<com.example.rentmycar.data.model.api.post.RentalPlan> {
                override fun onResponse(call: Call<com.example.rentmycar.data.model.api.post.RentalPlan>, response: Response<com.example.rentmycar.data.model.api.post.RentalPlan>) {


                    if (!response.isSuccessful) {

                        onResult(null)
                    }
                    onResult(response)
                }

                override fun onFailure(call: Call<com.example.rentmycar.data.model.api.post.RentalPlan>, t: Throwable) {
                    onResult(null)
                }
            })
        }

    }
}