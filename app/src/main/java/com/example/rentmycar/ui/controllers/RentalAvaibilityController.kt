package com.example.rentmycar.ui.controllers

import android.os.Build
import androidx.annotation.RequiresApi
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.LocalException
import com.example.rentmycar.data.model.api.post.Rental
import com.example.rentmycar.data.model.api.post.SelectedTimeSlot
import com.example.rentmycar.data.model.api.post.TimeSlot
import com.example.rentmycar.databinding.ModelCarAvailabilityTimeslotBinding
import com.example.rentmycar.databinding.ModelCarAvailabilityTitleBinding
import com.example.rentmycar.ui.epoxy.EmptyListEpoxyModel
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.rentmycar.rentmycar.epoxy.LoadingEpoxyModel
import com.rentmycar.rentmycar.epoxy.ViewBindingKotlinModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class RentalAvaibilityController  (
    private val timeslotSelected: (Int) -> Unit
): EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var rental: Rental? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (rental == null) {
            val localException = LocalException(
                HomeCustomerActivity.context.getString(R.string.no_car_found),
                HomeCustomerActivity.context.getString(R.string.no_car_available)
            )
            EmptyListEpoxyModel(localException).id("emptyList").addTo(this)
            return
        }

            rental?.selectedSlots?.forEach { timeslot ->
                TimeslotEpoxyModel(timeslot.id,
                        timeslot,
                        timeslotSelected)
            }


    }

    data class TimeslotEpoxyModel(
        val id: Int,
        val timeSlot: SelectedTimeSlot,
        val timeslotSelected: (Int) -> Unit
    ):ViewBindingKotlinModel<ModelCarAvailabilityTimeslotBinding>(R.layout.model_car_availability_timeslot){
        override fun ModelCarAvailabilityTimeslotBinding.bind(){
            val formattedStartAt = timeSlot.timeSlot?.startAt
            val formattedEndAt = timeSlot.timeSlot?.endAt
            timeslotCheckbox.text = "${timeSlot.date} : $formattedStartAt-$formattedEndAt"



            timeslotCheckbox.setOnClickListener {
                timeslotSelected(timeSlot.id)
            }
        }
    }



}