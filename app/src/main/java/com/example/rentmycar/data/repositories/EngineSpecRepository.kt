package com.example.rentmyengineSpec.data.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.api.request.EngineRequest
import com.example.rentmycar.data.api.request.EngineSpecRequest
import com.example.rentmycar.data.model.api.post.Engine
import com.example.rentmycar.data.model.api.post.EngineSpec
import com.example.rentmycar.data.room.EngineSpecRoom
import com.example.rentmycar.data.room.RentMyCarDatabase
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EngineSpecRepository {
    companion object {
        private fun client() = ServiceProvider.RentalApi.rentalClient
        private fun dao(context: Context) = RentMyCarDatabase.getInstance(context).engineSpecDao()
        private fun api() = ServiceProvider.RentalApi.retrofitService

        suspend fun getEngineSpecList(): List<EngineSpecRequest>? {
            var engineSpecList: List<EngineSpecRequest>? = null
            val request = client().getEngineSpec()
            if (request!!.failed || !request.isSuccessful) {
                return engineSpecList
            }
            if (request != null) {
                engineSpecList = request.body
            }

            return engineSpecList
        }

        suspend fun saveEngineSpec(context: Context, engineSpec: EngineSpecRoom): Long {
            return try {
                dao(context).createEngineSpec(engineSpec)
            } catch (e: Exception) {
                Log.e("Some Tag", e.toString(), e);
                Toast.makeText(context,
                    context.getString(R.string.no_database_connection),
                    Toast.LENGTH_LONG).show()
                return 0
            }
        }

        suspend fun getEngineSpec(context: Context, engineSpecId: Int): EngineSpecRoom? {
            return try {
                dao(context).getEngineSpec(engineSpecId)
            } catch (e: Exception) {

                Toast.makeText(
                    context,
                    context.getString(R.string.no_database_connection),
                    Toast.LENGTH_LONG
                ).show()
                return null
            }
        }


        suspend fun createEngineSpec(
            engineSpec: EngineSpec,
            onResult: (Response<EngineSpecRequest>?) -> Unit
        ) {
            var call = api().createEngineSpec(engineSpec)

            call.enqueue(object : Callback<EngineSpecRequest> {
                override fun onResponse(
                    call: Call<EngineSpecRequest>,
                    response: Response<EngineSpecRequest>
                ) {


                    if (!response.isSuccessful) {

                        onResult(null)
                    }
                    onResult(response)
                }

                override fun onFailure(call: Call<EngineSpecRequest>, t: Throwable) {
                    onResult(null)
                }
            })
        }
    }
}