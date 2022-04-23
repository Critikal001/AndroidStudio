package com.example.rentmycar.ui.controllers

import android.graphics.Color
import android.view.View
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.example.rentmycar.AppPreference
import com.example.rentmycar.R

import com.example.rentmycar.data.model.api.post.EngineType
import com.example.rentmycar.data.model.api.post.LocalException
import com.example.rentmycar.data.model.api.post.Location
import com.example.rentmycar.data.model.api.post.Rental
import com.example.rentmycar.databinding.*
import com.example.rentmycar.ui.epoxy.EmptyListEpoxyModel
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity

import com.rentmycar.rentmycar.epoxy.LoadingEpoxyModel
import com.rentmycar.rentmycar.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class RentalDetailFragmentController(
    private val onLocationBtnClicked: (Int) -> Unit,
    private val onEditLocationBtnClicked: (Int) -> Unit,
    private val onEditCarBtnClicked: (Int) -> Unit,
    private val onBookNowBtnClicked: (Int) -> Unit
) : EpoxyController() {
    private val preference = AppPreference(HomeCustomerActivity.context)
    private var hideEditButtons: Boolean = false

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

        if(preference.getUserId() != rental?.user?.userId) {
            hideEditButtons = true
        }

        TitleModel(
            rental = rental!!

        ).id("header").addTo(this)

        var items = rental?.images?.map { resource ->
            ImagesItemModel(resource.filePath).id(resource.id)
        }

        if (items == null) {

                items = listOf(
                    ImagesItemModel(
                        "https://i.ibb.co/gMTHyGk/4104f9a97970.png"
                    ).id("no_image"))

        }

        if (items != null) {
            CarouselModel_()
                .id("images_carousel")
                .padding(
                    Carousel.Padding.dp(8,0,8,0,8)
                )
                .models(items)
                .numViewsToShowOnScreen(1f)
                .addTo(this)
        }

        if (rental?.inUse == false) {
            ActionButtonEpoxyModel(rental, onBookNowBtnClicked).id("btn_action").addTo(this)
        }

        TitleEpoxyModel(rental!!, hideEditButtons, onEditCarBtnClicked).id("title").addTo(this)

        DataModel(
            title = HomeCustomerActivity.context.getString(R.string.brand),
            description = rental!!.car!!.model
        ).id("data_point_1").addTo(this)



        if (rental!!.location != null) {
            LocationEpoxyModel(
                location = rental!!.location,
                hideEditButtons,
                onEditLocationBtnClicked
            ).id("location").addTo(this)

            MapEpoxyModel(onLocationBtnClicked, locationId = rental!!.location?.locationId).id("map").addTo(this)
        }
    }


    data class TitleModel(
        val rental: Rental

    ) : ViewBindingKotlinModel<ModelCarDetailsTitleBinding>(R.layout.model_car_details_title) {

        override fun ModelCarDetailsTitleBinding.bind() {
            carTextView.text = rental.name

            when(rental.car?.engineType) {
                EngineType.BEVEngine -> {
                    carTypeTextView.text = HomeCustomerActivity.context.getString(R.string.car_type_bev)
                    carTypeImageView.setImageResource(R.drawable.ic_baseline_electric_car_24)
                }
                EngineType.FCEVEngine -> {
                    carTypeTextView.text = HomeCustomerActivity.context.getString(R.string.car_type_fcev)
                    carTypeImageView.setImageResource(R.drawable.ic_baseline_fcev)
                }
                EngineType.ICEEngine -> {
                    carTypeTextView.text = HomeCustomerActivity.context.getString(R.string.car_type_ice)
                    carTypeImageView.setImageResource(R.drawable.ic_baseline_local_gas_station_24)
                }
            }
        }
    }

    data class ImagesItemModel(
        val filePath: String,
    ): ViewBindingKotlinModel<ModelItemImagesBinding>(R.layout.model_item_images) {

        override fun ModelItemImagesBinding.bind() {
            Picasso.get().load(filePath).into(headerImageView)

            root.setOnClickListener {
                // unset carlist listener
            }
        }

    }

    data class DataModel(
        val title: String,
        val description: String
    ) : ViewBindingKotlinModel<ModelRentalDetailsDataBinding>(R.layout.model_rental_details_data) {

        override fun ModelRentalDetailsDataBinding.bind() {
            labelTextView.text = title
            textView.text = description
        }

    }

    data class MapEpoxyModel(
        val onLocationBtnClicked: (Int) -> Unit,
        val locationId: Int?
    ): ViewBindingKotlinModel<ModelMapBinding>(R.layout.model_map) {
        override fun ModelMapBinding.bind() {
            btnViewOnMap.setOnClickListener {
                if (locationId != null) {
                    onLocationBtnClicked(locationId)
                }
            }
        }

    }

    data class LocationEpoxyModel(
        val location: Location?,
        private val hideEditButton: Boolean,
        val onEditLocationBtnClicked: (Int) -> Unit
    ): ViewBindingKotlinModel<ModelCarDetailsLocationBinding>(R.layout.model_car_details_location) {

        override fun ModelCarDetailsLocationBinding.bind() {
            addressLine.text = location?.street
            postalCodeLine.text = location?.postalCode
            cityLine.text = location?.city
            countryLine.text = location?.country

            if (hideEditButton) {
                locationEditImageView.visibility = View.GONE
            }

            locationEditImageView.setOnClickListener {
                if (location?.locationId != null) {
                    locationEditImageView.setBackgroundColor(Color.parseColor("#BABABA"))
                    onEditLocationBtnClicked(location.locationId)
                }
            }
        }
    }

    class TitleEpoxyModel(
        val rental: Rental,
        private val hideEditButton: Boolean,
        val onEditCarBtnClicked: (Int) -> Unit
    ): ViewBindingKotlinModel<ModelCarHeaderBinding>(R.layout.model_car_header) {

        override fun ModelCarHeaderBinding.bind() {
            if (hideEditButton) {
                carEditImageView.visibility = View.GONE
            }

            carEditImageView.setOnClickListener {
                if (rental?.rentalId != null) {
                    carEditImageView.setBackgroundColor(Color.parseColor("#BABABA"))
                    onEditCarBtnClicked(rental.rentalId)
                }
            }
        }
    }

    data class ActionButtonEpoxyModel(
        val rental: Rental?,
        val onBookNowBtnClicked: (Int) -> Unit
    ): ViewBindingKotlinModel<BookRentalButtonBinding>(R.layout.book_rental_button) {
        override fun BookRentalButtonBinding.bind() {

            btnBookNow.setOnClickListener {
                if (rental?.rentalId != null) {
                     onBookNowBtnClicked(rental.rentalId)
                }
            }
        }
    }
}