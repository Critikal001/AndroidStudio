package com.example.rentmycar.ui.view.fragment

import android.icu.util.DateInterval
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.*
import com.example.rentmycar.ui.controllers.RentalOverviewController
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.example.rentmycar.ui.viewmodel.*
import com.google.type.DateTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CreateRentalOverview : Fragment() {
    private val timeSlotsList : MutableList<SelectedTimeSlot> = mutableListOf()
    private lateinit var rental : Rental
    private lateinit var car : Car
    private lateinit var rentalPlan: RentalPlan
    private lateinit var locationParam: Location
    private lateinit var rentalParam : Rental
    private lateinit var carParam : Car


    private lateinit var location: Location

    private val safeArgs: CreateRentalOverviewArgs by navArgs()

    private val carViewModel: CarViewModel by lazy {
        ViewModelProvider(this)[CarViewModel::class.java]
    }

    private val rentalViewModel: RentalViewModel by lazy {
        ViewModelProvider(this)[RentalViewModel::class.java]
    }

    private val rentalPlanViewModel: RentalPlanViewModel by lazy {
        ViewModelProvider(this)[RentalPlanViewModel::class.java]
    }


    private val locationViewModel: LocationViewModel by lazy {
        ViewModelProvider(this)[LocationViewModel::class.java]
    }
    private val epoxyController = RentalOverviewController(::onContinue)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.create_rental_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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

        rentalPlanViewModel.rentalPlanByIdLiveData.observe(viewLifecycleOwner){rentalPlanResponse ->
            if (rentalPlanResponse == null){
                Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }else{
                rentalPlan = RentalPlan(
                    0,
                    rentalPlanResponse.availableFrom,
                    rentalPlanResponse.availableUntil
                )
            }
        }

        getData()


        val epoxyRecyclerView = view?.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView?.setControllerAndBuildModels(epoxyController)
    }



    private fun getData() {
        // Retrieve location and car from Room database
        carViewModel.getCar(requireContext(), safeArgs.carId)

        rentalViewModel.getRental(requireContext(), safeArgs.rentalId)
        locationViewModel.getLocation(requireContext(), safeArgs.locationId)
        rentalPlanViewModel.getRentalPlanById(requireContext(), safeArgs.rentalPlanId)

        carViewModel.carResult.value?.let { carViewModel.getCar(requireContext(), it) }
        locationViewModel.locationResult.value?.let { locationViewModel.getLocation(requireContext(), it) }
        rentalViewModel.rentalResult.value?.let { rentalViewModel.getRental(requireContext(), it) }

    }


    private fun onContinue() {
        carViewModel.createCar(car)

        carViewModel.carResourceResult.observe(viewLifecycleOwner) { cars ->
            if (cars == null) {
                Toast.makeText(requireActivity(), "Failed to insert car", Toast.LENGTH_LONG).show()
                return@observe
            } else {
                carParam = Car(cars.registrationNr,
                    cars.constructionYear,
                    cars.mileage,
                    cars.model,
                    cars.power,
                    cars.engineType,
                    cars.fuelType,
                    cars.fuelUsePerKm,
                    cars.fuelPrice)
                locationViewModel.createLocation(location)
            }
        }

        locationViewModel.locationResourceResult.observe(viewLifecycleOwner) { locations ->
            if (locations == null) {
                Toast.makeText(requireActivity(), "Failed to insert car", Toast.LENGTH_LONG).show()
                return@observe
            } else {
                locationParam = Location(
                    locations.locationId,
                    locations.street,
                    locations.houseNumber,
                    locations.postalCode,
                    locations.city,
                    locations.country,
                    locations.latitude,
                    locations.longitude
                )
                var param = Rental(
                    rental.rentalId,
                    rental.name,
                    rental.price,
                    rental.mileage,
                    rental.inUse,
                    carParam,
                    locationParam,
                    null,
                    null,
                    null

                )
                rentalViewModel.createRental(param)
            }
        }

        rentalViewModel.rentalCreateResult.observe(viewLifecycleOwner){rentals ->
            if (rentals == null){
                Toast.makeText(requireActivity(), "Failed to insert rental", Toast.LENGTH_LONG).show()
                return@observe
            }else{
                saveTimeblocks(rentals.rentalId)

            }
        }

        rentalPlanViewModel.selectedSlotsLiveData.observe(viewLifecycleOwner){ timeSlots ->
            if (timeSlots == null){
                Toast.makeText(requireActivity(), "Failed to insert rental", Toast.LENGTH_LONG).show()
                return@observe
            }else{
                findNavController().navigate(R.id.create_rental_overview_to_dashboard)
            }
        }
        }

    fun saveTimeblocks(rentalId: Int) {
        rentalPlanViewModel.getSelectedTimeslots()

        rentalPlanViewModel.timeSlotLiveData.observe(viewLifecycleOwner) { timeSlots ->
            if (timeSlots == null) {
                Toast.makeText(requireActivity(), "Failed to insert car", Toast.LENGTH_LONG).show()
                return@observe
            } else {



                var startDate = LocalDate.parse(rentalPlan.availableFrom)

                val endDate = LocalDate.parse(rentalPlan.availableUntil)


                fun toLocalDates(): List<LocalDate> = generateSequence(startDate) { d ->
                    d.plusDays(1).takeIf { it < endDate }
                }.toList()

                toLocalDates().forEach { Date ->
                    timeSlots.forEach { slots->
                        timeSlotsList.add(SelectedTimeSlot(0, Date.toString(), false, slots))
                    }

                    rentalPlanViewModel.createSelectedTimeslots(rentalId, timeSlotsList.toList())
                }
            }
        }
    }
}













