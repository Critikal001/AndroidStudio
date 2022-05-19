package com.example.rentmycar.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rentmycar.R
import com.example.rentmycar.ui.controllers.RentalAvaibilityController
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.example.rentmycar.ui.view.fragment.createRental.CreateRentalFragmentDirections
import com.example.rentmycar.ui.viewmodel.AvailabilityViewModel
import com.example.rentmycar.ui.viewmodel.factory.AvailabilityViewModelFactory
import kotlinx.android.synthetic.main.book_rental_button.*

class CarAvaibilityFragment : Fragment() {
    private val safeArgs: CarAvaibilityFragmentArgs by navArgs()
    private val epoxyController = RentalAvaibilityController(::timeslotSelected)
    private lateinit var viewModel: AvailabilityViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(this, AvailabilityViewModelFactory(rentalId = safeArgs.rentalId))[AvailabilityViewModel::class.java]

        viewModel.rentalDetailResult.observe(viewLifecycleOwner) { pagedList ->
            if (pagedList == null){
                Toast.makeText(requireActivity(), HomeCustomerActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }else {
                epoxyController.rental = pagedList
            }
        }
        viewModel.getRentalById()
        return inflater.inflate(R.layout.fragment_car_avaibility, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    private fun timeslotSelected(timeSlotId: Int) {
        val directions = CarAvaibilityFragmentDirections.actionCarAvailabilityFragmentToReservationCreateFragment(safeArgs.rentalId, timeSlotId)
        findNavController().navigate(directions)

    }
}