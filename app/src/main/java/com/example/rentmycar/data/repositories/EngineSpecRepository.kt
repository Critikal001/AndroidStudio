package com.example.rentmyengineSpec.data.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.api.request.EngineSpecRequest
import com.example.rentmycar.data.model.api.post.EngineSpec
import com.example.rentmycar.data.room.EngineSpecRoom
import com.example.rentmycar.data.room.RentMyCarDatabase
import com.example.rentmycar.ui.view.activity.HomeProviderActivity

class EngineSpecRepository {
    private fun client() = ServiceProvider.RentalApi.rentalClient
    private fun dao(context : Context) = RentMyCarDatabase.getInstance(context).engineSpecDao()

    suspend fun getEngineSpecList(): List<EngineSpecRequest>? {
        var engineSpecList : List<EngineSpecRequest>? = null
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
            Toast.makeText(context, context.getString(R.string.no_database_connection), Toast.LENGTH_LONG).show()
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



    suspend fun createEngineSpec(engineSpec: EngineSpec): EngineSpecRequest? {
        var engineSpecRequest : EngineSpecRequest? = null
        val request = client().createEngineSpec(engineSpec)
        if (request!!.failed || !request.isSuccessful) {
            Toast.makeText(HomeProviderActivity.context, HomeProviderActivity.context.getString(R.string.error_put_engineSpec), Toast.LENGTH_LONG).show()
            return null
        }
        if (request != null) {
            engineSpecRequest = request.body
        }
        return engineSpecRequest
    }
}