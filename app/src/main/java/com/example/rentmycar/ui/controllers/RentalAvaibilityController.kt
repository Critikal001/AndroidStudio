package com.example.rentmycar.ui.controllers

import android.os.Build
import androidx.annotation.RequiresApi
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.*
import com.example.rentmycar.databinding.ModelCarAvailabilityTimeslotBinding
import com.example.rentmycar.databinding.ModelCarAvailabilityTitleBinding
import com.example.rentmycar.databinding.ModelItemImagesBinding
import com.example.rentmycar.databinding.RentalListItemBinding
import com.example.rentmycar.ui.epoxy.EmptyListEpoxyModel
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.rentmycar.rentmycar.epoxy.HeaderEpoxyModel
import com.rentmycar.rentmycar.epoxy.LoadingEpoxyModel
import com.rentmycar.rentmycar.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class RentalAvaibilityController  (
    private val timeslotSelected: (Int) -> Unit
): EpoxyController() {
    val today : MutableList<SelectedTimeSlot> = mutableListOf()
    val tomorrow : MutableList<SelectedTimeSlot> = mutableListOf()
    val dayAfter : MutableList<SelectedTimeSlot> = mutableListOf()

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

        HeaderEpoxyModel("Choose a time")
            .id("header").addTo(this)

        rental?.selectedSlots?.forEach { timeslot ->
            if(LocalDate.parse(timeslot.date) == LocalDate.now()) {
                today.add(timeslot)
            }

            if(LocalDate.parse(timeslot.date) == LocalDate.now().plusDays(1)) {
                tomorrow.add(timeslot)
            }

            if(LocalDate.parse(timeslot.date) == LocalDate.now().plusDays(3)) {
                dayAfter.add(timeslot)
            }

        }

        TimeslotGridTitleEpoxyModel(LocalDate.now().toString()).id("Title").addTo(this)
        today.forEach{ timeSlot->
            TimeslotEpoxyModel(
                rental!!.rentalId,
                timeSlot,
                timeslotSelected).id("header_{$timeSlot.id}").addTo(this)
        }

        TimeslotGridTitleEpoxyModel(LocalDate.now().plusDays(1).toString()).id("Title").addTo(this)
        tomorrow.forEach{ timeSlot->
            TimeslotEpoxyModel(
                rental!!.rentalId,
                timeSlot,
                timeslotSelected).id("header_{$timeSlot.id}").addTo(this)
        }

        TimeslotGridTitleEpoxyModel(LocalDate.now().plusDays(2).toString()).id("Title").addTo(this)
        dayAfter.forEach{ timeSlot->
            TimeslotEpoxyModel(
                rental!!.rentalId,
                timeSlot,
                timeslotSelected).id("header_{$timeSlot.id}").addTo(this)
        }

        if (rental == null) {
            val localException = LocalException(
                HomeCustomerActivity.context.getString(R.string.no_car_found),
                HomeCustomerActivity.context.getString(R.string.no_car_available)
            )
            EmptyListEpoxyModel(localException).id("emptyList").addTo(this)
            return
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
            timeslotCheckbox.text = "$formattedStartAt-$formattedEndAt"
            timeslotCheckbox.setOnClickListener {
                timeslotSelected(timeSlot.id)
            }
        }
    }

    data class TimeslotGridTitleEpoxyModel(
        val title: String
    ): ViewBindingKotlinModel<ModelCarAvailabilityTitleBinding>(R.layout.model_car_availability_title) {

        override fun ModelCarAvailabilityTitleBinding.bind() {
            titleTextView.text = title
        }
    }
}