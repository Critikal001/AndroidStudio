package com.example.rentmycar.ui.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.*
import com.example.rentmycar.ui.controllers.RentalOverviewController
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.example.rentmycar.ui.viewmodel.*

class CreateRentalOverview : Fragment() {
    private lateinit var rental :Rental
    private lateinit var car : Car
    private lateinit var engine: Engine
    private lateinit var engineSpec: EngineSpec
    private lateinit var location: Location
    private lateinit var provider: Provider
    private val safeArgs: CreateRentalOverviewArgs by navArgs()

    private val carViewModel: CarViewModel by lazy {
        ViewModelProvider(this)[CarViewModel::class.java]
    }

    private val rentalViewModel: RentalViewModel by lazy {
        ViewModelProvider(this)[RentalViewModel::class.java]
    }


    private val locationViewModel: LocationViewModel by lazy {
        ViewModelProvider(this)[LocationViewModel::class.java]
    }
    private val epoxyController = RentalOverviewController(::onContinue)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        rentalViewModel.rentalRoomLiveData.observe(viewLifecycleOwner) {rentalResponse ->
            epoxyController.rental = rentalResponse
            if (rentalResponse == null) {
                Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }else{
                rental = Rental(0, rentalResponse.name, rentalResponse.price, rentalResponse.mileage, false, null, null, null, null, null)
            }
        }

        carViewModel.carRoomLiveData.observe(viewLifecycleOwner) {carResponse ->
            epoxyController.car = carResponse
            if (carResponse == null) {
                Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }else{
                car = Car(0, carResponse.constructionYear, carResponse.mileage, carResponse.model, carResponse.power, carResponse.engineType, carResponse.fuelType, carResponse.fuelUsePerKm, carResponse.fuelPrice)
            }
        }


        locationViewModel.locationRoomLiveData.observe(viewLifecycleOwner) {locationResponse ->
            epoxyController.location = locationResponse
            if (locationResponse == null){
                Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }else{
                location = Location(0, locationResponse.street, locationResponse.houseNumber,locationResponse.postalCode,locationResponse.city,locationResponse.country, locationResponse.longitude, locationResponse.latitude)
            }
        }
        return inflater.inflate(R.layout.create_rental_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getData()


        val epoxyRecyclerView = view?.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView?.setControllerAndBuildModels(epoxyController)
    }



    private fun getData() {
        // Retrieve location and car from Room database
        carViewModel.getCar(requireContext(), safeArgs.carId)

        rentalViewModel.getRental(requireContext(), safeArgs.rentalId)
        locationViewModel.getLocation(requireContext(), safeArgs.locationId)

        carViewModel.carResult.value?.let { carViewModel.getCar(requireContext(), it) }
        locationViewModel.locationResult.value?.let { locationViewModel.getLocation(requireContext(), it) }
        rentalViewModel.rentalResult.value?.let { rentalViewModel.getRental(requireContext(), it) }
    }

    private fun onContinue() {

                                var paramCar = Car(car.registrationNr, car.constructionYear, car.mileage, car.model, car.power, car.engineType,car.fuelType, car.fuelUsePerKm, car.fuelPrice)
                                carViewModel.createCar(paramCar)
                                carViewModel.carResourceResult.observe(viewLifecycleOwner){cars ->
                                    if (cars == null){
                                        Toast.makeText(requireActivity(), "Failed to insert car", Toast.LENGTH_LONG).show()
                                        return@observe
                                    }else{
                                        car = Car(0, cars.constructionYear, cars.mileage, cars.model,cars.power,cars.engineType,cars.fuelType,cars.fuelUsePerKm,cars.fuelPrice)

                                        var paramRental = Rental(rental.rentalId, rental.name, rental.price, rental.mileage, rental.inUse, car, location, null, null, null)
                                        rentalViewModel.createRental(paramRental)
                                        rentalViewModel.rentalListLiveData.observe(viewLifecycleOwner){rentals ->
                                            if (rentals == null){
                                                Toast.makeText(requireActivity(), "Failed to insert rental", Toast.LENGTH_LONG).show()
                                                return@observe
                                            }else{
                                                findNavController().navigate(R.id.create_rental_overview_to_dashboard)
                                            }
                                        }
                                    }
                                }
                            }








}