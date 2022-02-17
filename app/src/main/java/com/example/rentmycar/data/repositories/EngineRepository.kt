package com.example.rentmycar.data.repositories

import android.content.Context
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.api.request.EngineRequest
import com.example.rentmycar.data.model.api.post.Engine
import com.example.rentmycar.data.room.EngineRoom
import com.example.rentmycar.data.room.RentMyCarDatabase
import com.example.rentmycar.ui.view.activity.HomeProviderActivity

class EngineRepository {
    private fun client() = ServiceProvider.RentalApi.rentalClient
    private fun dao(context : Context) = RentMyCarDatabase.getInstance(context).engineDao()

    suspend fun getEngineList(): List<EngineRequest>? {
        var engineList : List<EngineRequest>? = null
        val request = client().getEngine()
        if (request!!.failed || !request.isSuccessful) {
            return engineList
        }
        if (request != null) {
            engineList = request.body
        }

        return engineList
    }

    suspend fun saveEngine(context: Context, engine: EngineRoom): Long {
        return try {
            dao(context).createEngine(engine)
        } catch (e: Exception) {
            Toast.makeText(context, context.getString(R.string.no_database_connection), Toast.LENGTH_LONG).show()
            return 0
        }
    }

    suspend fun getEngine(context: Context, engineId: Int): EngineRoom? {
        return try {
            dao(context).getEngine(engineId)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                context.getString(R.string.no_database_connection),
                Toast.LENGTH_LONG
            ).show()
            return null
        }
    }



    suspend fun createEngine(engine: Engine): EngineRequest? {
        var engineRequest : EngineRequest? = null
        val request = client().createEngine(engine)
        if (request!!.failed || !request.isSuccessful) {
            Toast.makeText(HomeProviderActivity.context, HomeProviderActivity.context.getString(R.string.error_put_engine), Toast.LENGTH_LONG).show()
            return null
        }
        if (request != null) {
            engineRequest = request.body
        }
        return engineRequest
    }
}