package com.example.rentmycar.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rentmycar.R
import com.example.rentmycar.ui.controllers.RentalAvaibilityController
import com.example.rentmycar.ui.viewmodel.AvailabilityViewModel

class CarAvaibilityFragment : Fragment() {
    private val safeArgs: CarAvaibilityFragmentArgs by navArgs()
    private val epoxyController = RentalAvaibilityController(::timeslotSelected)
    private lateinit var viewModel: AvailabilityViewModel
    private var selectedTimeslots: MutableList<TimeslotIdRequest> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_car_availability, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (selectedTimeslots.size == 0) {
            btnBookNow.isEnabled = false
        }

        viewModel =
            ViewModelProvider(this, AvailabilityViewModelFactory(carId = safeArgs.carId))[AvailabilityViewModel::class.java]

        viewModel.availabilityPagedListLiveData.observe(viewLifecycleOwner) { pagedList ->
            epoxyController.submitList(pagedList)
        }

        // Store selected timeslots as parcelable and continue to insurance selection with this data
        btnBookNow.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelableArrayList("selectedTimeslots", ArrayList<TimeslotIdRequest>(selectedTimeslots))
            bundle.putInt("rentalPlanId", safeArgs.rentalPlanId)

            val insuranceFragment = InsuranceSelectFragment()
            insuranceFragment.arguments = bundle

            val fragmentTransaction = childFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.availability_layout, insuranceFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView).setController(epoxyController)
    }

    private fun timeslotSelected(id: Int) {
        selectedTimeslots.add(TimeslotIdRequest(id = id))
        btnBookNow.isEnabled = true
    }
}