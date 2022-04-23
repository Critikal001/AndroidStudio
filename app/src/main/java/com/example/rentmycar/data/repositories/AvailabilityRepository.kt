package com.example.rentmycar.data.repositories

import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.model.api.post.SelectedTimeSlot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AvailabilityRepository {
    companion object {
        fun client() = ServiceProvider.RentalApi.retrofitService

        fun createTimeSlots(
            id: Int,
            timeSlots: List<SelectedTimeSlot>,
            onResult: (Response<SelectedTimeSlot>?) -> Unit
        ) {
            val request = client().createSelectedTimeslot(id, timeSlots)

            request.enqueue(object : Callback<SelectedTimeSlot> {
                override fun onResponse(
                    call: Call<SelectedTimeSlot>,
                    response: Response<SelectedTimeSlot>
                ) {


                    if (!response.isSuccessful) {

                        onResult(null)
                    }
                    onResult(response)
                }

                override fun onFailure(call: Call<SelectedTimeSlot>, t: Throwable) {
                    onResult(null)
                }
            })
        }
    }
}