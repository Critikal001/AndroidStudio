package com.example.rentmycar.ui.controllers

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.example.rentmycar.R

import com.example.rentmycar.data.model.api.post.EngineType
import com.example.rentmycar.data.model.api.post.LocalException
import com.example.rentmycar.data.model.api.post.Rental
import com.example.rentmycar.databinding.ModelItemImagesBinding
import com.example.rentmycar.databinding.RentalListItemBinding
import com.example.rentmycar.ui.epoxy.EmptyListEpoxyModel
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity

import com.rentmycar.rentmycar.epoxy.HeaderEpoxyModel
import com.rentmycar.rentmycar.epoxy.LoadingEpoxyModel
import com.rentmycar.rentmycar.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso


class RentalListController(
    private val onCarSelected: (Int) -> Unit
) : EpoxyController() {
    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var rentals: List<Rental?> = emptyList()
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
        Thread.sleep(2000);

        HeaderEpoxyModel(HomeCustomerActivity.context.getString(R.string.choose_a_car))
            .id("header").addTo(this)

        if (rentals.isEmpty()) {
            val localException = LocalException(
                HomeCustomerActivity.context.getString(R.string.no_cars_found),
                HomeCustomerActivity.context.getString(R.string.no_cars_available)
            )
            EmptyListEpoxyModel(localException).id("emptyList").addTo(this)
            return
        }

        rentals.forEach { rental ->
            if (rental?.inUse == false) {
                CarListItemModel(rental, onCarSelected).id("header_{$rental.id}").addTo(this)

                var items = rental.images?.map { resource ->
                    ImagesItemModel(
                        rental.rentalId,
                        resource.filePath,
                        onCarSelected
                    ).id(resource.id)
                }

                if (items?.isEmpty() == true) {
                    items = listOf(
                        ImagesItemModel(
                            rental.rentalId,
                            "https://i.ibb.co/gMTHyGk/4104f9a97970.png",
                            onCarSelected
                        ).id("no_image"))
                }

                if (items != null) {
                    CarouselModel_()
                        .id("images_carousel_{${rental.rentalId}")
                        .padding(
                            Carousel.Padding.dp(8, 0, 8, 0, 8)
                        )
                        .models(items)
                        .numViewsToShowOnScreen(1f)
                        .addTo(this)
                }


            }
        }




    }
    data class CarListItemModel(
        val rental: Rental,
        val onCarSelected: (Int) -> Unit
    ): ViewBindingKotlinModel<RentalListItemBinding>(R.layout.rental_list_item) {

        override fun RentalListItemBinding.bind() {
            titleTextView.text = rental.name
            when(rental.car?.engineType) {
                EngineType.BEVEngine -> {
                    carTypeImageView.setImageResource(R.drawable.ic_baseline_electric_car_24)
                }
                EngineType.FCEVEngine -> {
                    carTypeImageView.setImageResource(R.drawable.ic_baseline_fcev)
                }
                EngineType.ICEEngine -> {
                    carTypeImageView.setImageResource(R.drawable.ic_baseline_local_gas_station_24)
                }
            }

            root.setOnClickListener {
                onCarSelected(rental.rentalId)
            }
        }
    }

    data class ImagesItemModel(
        val carId: Int,
        val filePath: String,
        val onCarSelected: (Int) -> Unit
    ): ViewBindingKotlinModel<ModelItemImagesBinding>(R.layout.model_item_images) {

        override fun ModelItemImagesBinding.bind() {
            Picasso.get().load(filePath).into(headerImageView)

            root.setOnClickListener {
                onCarSelected(carId)
            }
        }
    }
}