package com.example.rentmycar.ui.controllers

import com.airbnb.epoxy.EpoxyController
import com.example.rentmycar.R
import com.example.rentmycar.data.room.*
import com.example.rentmycar.databinding.*
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.rentmycar.rentmycar.epoxy.HeaderEpoxyModel
import com.rentmycar.rentmycar.epoxy.LoadingEpoxyModel
import com.rentmycar.rentmycar.epoxy.ViewBindingKotlinModel
import kotlin.reflect.KFunction0

class RentalOverviewController(
    private val clickContinue: KFunction0<Unit>
): EpoxyController() {

    private var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var car: CarRoom? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }



    var rental: RentalRoom? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    var location: LocationRoom? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }


    override fun buildModels() {
//        if (isLoading) {
//            LoadingEpoxyModel().id("loading").addTo(this)
//            return
//        }

        HeaderEpoxyModel(HomeProviderActivity.context.getString(R.string.car_overview))
            .id("header").addTo(this)

        rentalModel(rental).id("rental").addTo(this)
        carModel(car).id("car").addTo(this)

        locationModel(location).id("location").addTo(this)

        ButtonsEpoxyModel(clickContinue).id("buttons").addTo(this)

    }





    data class carModel(
        val car: CarRoom?
    ): ViewBindingKotlinModel<ModelCarDetailBinding>(R.layout.model_car_detail) {

        override fun ModelCarDetailBinding.bind() {
            constructionYearLine.text = car?.constructionYear.toString()
            mileageLine.text = car?.mileage.toString()
            modelLine.text = car?.model
        }
    }

    data class rentalModel(
        val rental: RentalRoom?
    ): ViewBindingKotlinModel<ModelRentalDetailBinding>(R.layout.model_rental_detail) {

        override fun ModelRentalDetailBinding.bind() {
            nameLine.text = rental?.name
            priceLine.text = rental?.price.toString()
            mileageLine.text = rental?.mileage.toString()
        }
    }



    data class locationModel(
        val location: LocationRoom?
    ): ViewBindingKotlinModel<ModelLocationDetailBinding>(R.layout.model_location_detail) {
        override fun ModelLocationDetailBinding.bind() {
            cityLine.text = location?.city
            addressLine.text = location?.houseNumber
        }
    }

    data class ButtonsEpoxyModel(
        private val onContinue: KFunction0<Unit>
    ): ViewBindingKotlinModel<ModelButtonBinding>(R.layout.model_button) {
        override fun ModelButtonBinding.bind() {
            btnContinue.setOnClickListener {
                onContinue()
            }
        }
    }

}