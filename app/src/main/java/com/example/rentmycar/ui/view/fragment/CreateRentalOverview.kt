package com.example.rentmycar.ui.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.*
import com.example.rentmycar.ui.controllers.RentalOverviewController
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.example.rentmycar.ui.viewmodel.*
import com.example.rentmyengineSpec.ui.viewmodel.EngineSpecViewModel

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

    private val engineViewModel: EngineViewModel by lazy {
        ViewModelProvider(this)[EngineViewModel::class.java]
    }

    private val engineSpecViewModel: EngineSpecViewModel by lazy {
        ViewModelProvider(this)[EngineSpecViewModel::class.java]
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

        getData()
        observeData()

        val epoxyRecyclerView = view?.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView?.setControllerAndBuildModels(epoxyController)
    }

    private fun observeData(){
        rentalViewModel.rentalRoomLiveData.observe(viewLifecycleOwner) {rentalResponse ->
            epoxyController.rental = rentalResponse
            if (rentalResponse == null) {
                Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }else{
                rental = Rental(rentalResponse.name, rentalResponse.price, rentalResponse.mileage, false, car, location, provider)
            }
        }

        carViewModel.carRoomLiveData.observe(viewLifecycleOwner) {carResponse ->
            epoxyController.car = carResponse
            if (carResponse == null) {
                Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }else{
                car = Car(carResponse.constructionYear, carResponse.mileage, carResponse.model, engine)
            }
        }

        engineViewModel.engineRoomLiveData.observe(viewLifecycleOwner) {engineResponse ->
            epoxyController.engine = engineResponse
            if (engineResponse == null) {
                Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }else{
                engine = Engine(engineResponse.power, engineSpec)
            }
        }

        engineSpecViewModel.engineSpecRoomLiveData.observe(viewLifecycleOwner) {engineSpecResponse ->
            epoxyController.engineSpec = engineSpecResponse
            if (engineSpecResponse == null) {
                Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }else{
                engineSpec = EngineSpec(engineSpecResponse.engineType, engineSpecResponse.fuelType, engineSpecResponse.fuelUsePerKm, engineSpecResponse.fuelPrice)
            }
        }

    }

    private fun getData() {
        // Retrieve location and car from Room database
        carViewModel.getCar(requireContext(), safeArgs.carId)
        engineViewModel.getEngine(requireContext(), safeArgs.engineId)
        engineSpecViewModel.getEngineSpec(requireContext(),safeArgs.engineSpecId)
        rentalViewModel.getRental(requireContext(), safeArgs.rentalId)
        locationViewModel.getLocation(requireContext(), safeArgs.locationId)

        carViewModel.carResult.value?.let { carViewModel.getCar(requireContext(), it) }
        locationViewModel.locationResult.value?.let { locationViewModel.getLocation(requireContext(), it) }
        engineSpecViewModel.engineSpecResult.value?.let { engineSpecViewModel.getEngineSpec(requireContext(), it) }
        engineViewModel.engineResult.value?.let { engineViewModel.getEngine(requireContext(), it) }
        rentalViewModel.rentalResult.value?.let { rentalViewModel.getRental(requireContext(), it) }
    }

    private fun onContinue() {
        engineSpecViewModel.createEngineSpec(engineSpec)
        engineViewModel.createEngine(engine)
        carViewModel.createCar(car)
        locationViewModel.createLocation(location)
        findNavController().navigate(R.id.create_rental_overview_to_dashboard)
    }

}