package com.example.rentmycar.ui.controllers

import com.airbnb.epoxy.EpoxyController
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.LocalException
import com.example.rentmycar.data.model.api.post.Reservation
import com.example.rentmycar.databinding.ModelReservationListItemBinding
import com.example.rentmycar.ui.epoxy.EmptyListEpoxyModel
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.rentmycar.rentmycar.epoxy.HeaderEpoxyModel
import com.rentmycar.rentmycar.epoxy.LoadingEpoxyModel
import com.rentmycar.rentmycar.epoxy.ViewBindingKotlinModel
import java.text.SimpleDateFormat
import java.util.*

class ReservationListController(
    private val onReservationSelected: (String) -> Unit
): EpoxyController() {
    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var reservations: List<Reservation?> = emptyList()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        HeaderEpoxyModel(
            HomeCustomerActivity.context.getString(R.string.my_reservations)
        ).id("header").addTo(this)

        if (reservations.isEmpty()) {
            val localException = LocalException(
                HomeCustomerActivity.context.getString(R.string.no_reservations_found),
                HomeCustomerActivity.context.getString(R.string.no_reservations_available)
            )
            EmptyListEpoxyModel(localException).id("emptyList").addTo(this)
            return
        }

        reservations.forEach { reservation ->
            if (reservation != null) {
                ReservationListItemEpoxyModel(
                    reservation.rental.name,
                    reservation,
                    onReservationSelected
                ).id(reservation.reservationId).addTo(this)
            }
        }
    }

    data class ReservationListItemEpoxyModel(
        val reservationNumber: String?,
        val reservation: Reservation,
        val onReservationSelected: (String) -> Unit
    ): ViewBindingKotlinModel<ModelReservationListItemBinding>(R.layout.model_reservation_list_item) {

        override fun ModelReservationListItemBinding.bind() {
            val startAt = reservation.selectedSlot.timeSlot?.startAt
            val endAt = reservation.selectedSlot.timeSlot?.endAt

            reservationTextView.text = reservationNumber
            reservationItalicTextView.text =
                HomeCustomerActivity.context.getString(R.string.reservation_list_time, startAt, endAt)



            root.setOnClickListener {
                onReservationSelected(reservationNumber!!)
            }
        }

    }
}