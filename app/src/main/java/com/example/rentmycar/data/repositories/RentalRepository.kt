package com.example.rentmycar.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.icu.number.IntegerWidth
import android.media.Image
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.model.api.post.Images

import com.example.rentmycar.data.model.api.post.Rental
import com.example.rentmycar.data.room.RentMyCarDatabase
import com.example.rentmycar.data.room.RentalRoom
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class RentalRepository {
    companion object {

        private fun api() = ServiceProvider.RentalApi.retrofitService
        private var rental :Rental? = null
        private var reservationList: ArrayList<Rental> = ArrayList<Rental>()
        private val reservations: MutableLiveData<List<Rental>> = MutableLiveData<List<Rental>>()
        private fun dao(context: Context) = RentMyCarDatabase.getInstance(context).rentalDao()



        fun getRentalList(context: Context): MutableList<Rental> {
//            val call = api().getRentalById(5)
              val call = api().getRentalList()
            var rentalList = mutableListOf<Rental>()


//                call.enqueue(object : Callback<Rental> {
//                    override fun onResponse(
//                        call: Call<Rental>,
//                        response: Response<Rental>,
//                    ) {
//                        val response = response.body()
//                        reservations.postValue(listOf(response!!))
//                    }
//
//                    override fun onFailure(call: Call<Rental>, t: Throwable) {}
//                })

            call.enqueue(object : Callback<List<Rental>> {
                override fun onResponse(
                    call: Call<List<Rental>>,
                    response: Response<List<Rental>>,
                ) {
                    response.body()?.forEach { item ->
                        rentalList.add(item)
                    }
                }

                override fun onFailure(call: Call<List<Rental>>, t: Throwable) {}
            })
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


        suspend fun createRental(rental: Rental, onResult: (Response<Rental>?) -> Unit) {
            var call = api().createRental(rental)

            call.enqueue(object : Callback<Rental> {
                override fun onResponse(
                    call: Call<Rental>,
                    response: Response<Rental>,
                ) {


                    if (!response.isSuccessful) {

                        onResult(null)
                    }
                    onResult(response)
                }

                override fun onFailure(call: Call<Rental>, t: Throwable) {
                    onResult(null)
                }
            })
        }

        fun getRentalById(rentalId: Int, onResult: (Response<Rental>?) -> Unit){

            val request  = api().getRentalById(rentalId)

                request.enqueue(object : Callback<Rental> {
                    override fun onResponse(
                        call: Call<Rental>,
                        response: Response<Rental>,
                    ) {


                        if (!response.isSuccessful) {

                            onResult(null)
                        }
                        onResult(response)
                    }

                    override fun onFailure(call: Call<Rental>, t: Throwable) {
                        onResult(null)
                    }
                })
        }


        suspend fun uploadImage(id : Int,image: MultipartBody.Part, onResult: (Response<Images>?) -> Unit)  {
            val request = api().postRentalImage(id , image)

            request.enqueue(object : Callback<Images> {
                override fun onResponse(
                    call: Call<Images>,
                    response: Response<Images>,
                ) {


                    if (!response.isSuccessful) {

                        onResult(null)
                    }
                    onResult(response)
                }

                override fun onFailure(call: Call<Images>, t: Throwable) {
                    onResult(null)
                }
            })
        }
    }

}