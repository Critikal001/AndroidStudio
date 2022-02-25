package com.example.rentmycar.ui.controllers


import android.widget.AutoCompleteTextView
import com.airbnb.epoxy.EpoxyController
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.Reservation
import com.example.rentmycar.databinding.ModelReservationCreateButtonsBinding
import com.example.rentmycar.databinding.ModelReservationCreateDataPointBinding
import com.example.rentmycar.databinding.ModelReservationCreateHeaderBinding
import com.example.rentmycar.databinding.ModelReservationCreatePaymentMethodBinding
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity

import com.rentmycar.rentmycar.epoxy.LoadingEpoxyModel
import com.rentmycar.rentmycar.epoxy.ViewBindingKotlinModel


import java.text.SimpleDateFormat
import java.util.*

class ReservationEpoxyController(
    private val onBtnBackClicked: () -> Unit,
    private val onBtnPayClicked: (String) -> Unit,
    private val dropdownFieldBinding: (AutoCompleteTextView) -> Unit
): EpoxyController() {
    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var reservation: Reservation? = null
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    var isDetailsView: Boolean = false
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        ReservationHeaderEpoxyModel(reservation?.reservationNumber, reservation?.status)
            .id("header").addTo(this)

        ReservationDataPointEpoxyModel(
            HomeCustomerActivity.context.getString(R.string.reservation_number),
            reservation?.reservationNumber,

        ).id("data_point_1").addTo(this)

        ReservationDataPointEpoxyModel(
            HomeCustomerActivity.context.getString(R.string.price_product),
            HomeCustomerActivity.context.getString(R.string.price_amount_reservation, reservation?.price),


            ).id("data_point_2").addTo(this)

        ReservationDataPointEpoxyModel(
            HomeCustomerActivity.context.getString(R.string.price_reservation_insurance),
            HomeCustomerActivity.context.getString(R.string.price_amount_reservation, reservation?.price),

            ).id("data_point_3").addTo(this)

        ReservationDataPointEpoxyModel(
            HomeCustomerActivity.context.getString(R.string.price_reservation),
            HomeCustomerActivity.context.getString(R.string.price_amount_reservation, reservation?.price),

            ).id("data_point_4").addTo(this)

        InsuranceDataPointEpoxyModel(
            HomeCustomerActivity.context.getString(R.string.insurance_type),

            true,

        ).id("data_point_5").addTo(this)

        InsuranceDataPointEpoxyModel(
            HomeCustomerActivity.context.getString(R.string.description),
            true
        ).id("data_point_6").addTo(this)

        ReservationStatusEpoxyModel(
            HomeCustomerActivity.context.getString(R.string.status),
            reservation?.status
        ).id("data_point_7").addTo(this)

        ReservationTimeDataPointEpoxyModel(
            HomeCustomerActivity.context.getString(R.string.start_date),
        ).id("data_point_8").addTo(this)

        ReservationTimeDataPointEpoxyModel(
            HomeCustomerActivity.context.getString(R.string.end_date),

        ).id("data_point_9").addTo(this)

        if (isDetailsView && reservation?.paidAt != null) {
            ReservationTimeDataPointEpoxyModel(
                HomeCustomerActivity.context.getString(R.string.paid_at),

            ).id("data_point_10").addTo(this)
        }

        // If reservation status is not final show buttons and payment method dropdown
        if (!isDetailsView) {
            ReservationPaymentMethodEpoxyModel(dropdownFieldBinding).id("payment_method_selector").addTo(this)

            ReservationButtonsEpoxyModel(
                onBtnBackClicked,
                onBtnPayClicked,
                reservation?.reservationNumber
            ).id("buttons").addTo(this)
        }
    }

    data class ReservationHeaderEpoxyModel(
        val reservationNumber: String?,
        val status: String?
    ): ViewBindingKotlinModel<ModelReservationCreateHeaderBinding>(R.layout.model_reservation_create_header) {
        override fun ModelReservationCreateHeaderBinding.bind() {

            reservationHeaderTextView.text = reservationNumber

            when(status) {
                "COMPLETED" -> {
                }
                "PENDING" -> {
                }
                "CANCELED" -> {
                }
                "EXPIRED" -> {
                }
            }
        }
    }

    data class ReservationDataPointEpoxyModel(
        val title: String?,
        val description: String?,

    ): ViewBindingKotlinModel<ModelReservationCreateDataPointBinding>(R.layout.model_reservation_create_data_point) {
        override fun ModelReservationCreateDataPointBinding.bind() {
            titleTextView.text = title
            descriptionTextView.text = description
        }
    }

    data class InsuranceDataPointEpoxyModel(
        val title: String?,

        val isType: Boolean,

    ): ViewBindingKotlinModel<ModelReservationCreateDataPointBinding>(R.layout.model_reservation_create_data_point) {

        override fun ModelReservationCreateDataPointBinding.bind() {

            titleTextView.text = title

//            when(description) {
//                "ALL_RISK" -> {
//                    if (isType) {
//                        descriptionTextView.text =
//                                HomeCustomerActivity.context.getString(R.string.title_all_risk)
//                        } else {
//                        descriptionTextView.text =
//                            HomeCustomerActivity.context.getString(R.string.description_all_risk)
//                    }
//                }
//                "ALL_RISK_INTERNATIONAL" -> {
//                    if (isType) {
//                        descriptionTextView.text =
//                            HomeCustomerActivity.context.getString(R.string.title_all_risk_international)
//                    } else {
//                        descriptionTextView.text =
//                            HomeCustomerActivity.context.getString(R.string.description_all_risk_international)
//                    }
//                }
//                "BASIC_COVERAGE" -> {
//                    if (isType) {
//                        descriptionTextView.text =
//                            HomeCustomerActivity.context.getString(R.string.title_basic_coverage)
//                    } else {
//                        descriptionTextView.text =
//                            HomeCustomerActivity.context.getString(R.string.description_basic_coverage)
//                    }
//                }
//                "BASIC_COVERAGE_INTERNATIONAL" -> {
//                    if (isType) {
//                        descriptionTextView.text =
//                            HomeCustomerActivity.context.getString(R.string.title_basic_coverage_international)
//                    } else {
//                        descriptionTextView.text =
//                            HomeCustomerActivity.context.getString(R.string.description_basic_coverage_international)
//                    }
//                }
//            }
        }
    }

    data class ReservationStatusEpoxyModel(
        val title: String?,
        val description: String?
    ): ViewBindingKotlinModel<ModelReservationCreateDataPointBinding>(R.layout.model_reservation_create_data_point) {

        override fun ModelReservationCreateDataPointBinding.bind() {

            titleTextView.text = title

            when(description) {
                "COMPLETED" -> {
                    descriptionTextView.text =
                    HomeCustomerActivity.context.getString(R.string.status_completed)
                }
                "PENDING" -> {
                    descriptionTextView.text =
                    HomeCustomerActivity.context.getString(R.string.status_pending)
                }
                "CANCELED" -> {
                    descriptionTextView.text =
                    HomeCustomerActivity.context.getString(R.string.status_canceled)
                }
                "EXPIRED" -> {
                    descriptionTextView.text =
                    HomeCustomerActivity.context.getString(R.string.status_expired)
                }
            }
        }
    }

    class ReservationPaymentMethodEpoxyModel(
        val dropdownFieldBinding: (AutoCompleteTextView) -> Unit
    ):
        ViewBindingKotlinModel<ModelReservationCreatePaymentMethodBinding>(R.layout.model_reservation_create_payment_method) {
        override fun ModelReservationCreatePaymentMethodBinding.bind() {
            dropdownFieldBinding(autoCompleteTextView)
        }
    }

    class ReservationButtonsEpoxyModel(
        val onBtnBackClicked: () -> Unit,
        val onBtnPayClicked: (String) -> Unit,
        val reservationNumber: String?
    ): ViewBindingKotlinModel<ModelReservationCreateButtonsBinding>(R.layout.model_reservation_create_buttons) {
        override fun ModelReservationCreateButtonsBinding.bind() {

            btnBack.setOnClickListener {
                onBtnBackClicked()
            }

            btnContinue.setOnClickListener {
                if (reservationNumber != null) {
                    onBtnPayClicked(reservationNumber)
                }
            }
        }
    }

    data class ReservationTimeDataPointEpoxyModel(
        val title: String?

    ): ViewBindingKotlinModel<ModelReservationCreateDataPointBinding>(R.layout.model_reservation_create_data_point) {
        override fun ModelReservationCreateDataPointBinding.bind() {
            titleTextView.text = title

        }

        private fun convertDate(input: String?): String? {
            if (input != null) {
                val date = SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss",
                    Locale.getDefault()
                ).parse(input)
                val format = SimpleDateFormat(
                    "HH:mm '('dd-MM-yyy')'",
                    Locale.getDefault()
                )
                return format.format(date!!)
            }
            return input
        }
    }
}