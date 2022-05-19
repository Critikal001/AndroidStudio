package com.example.rentmycar.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rentmycar.R
import com.example.rentmycar.ui.controllers.ReservationListController
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.example.rentmycar.ui.viewmodel.ReservationViewModel


class ReservationListFragment : Fragment() {
    private val epoxyController = ReservationListController(::onReservationSelected)

    private val viewModel: ReservationViewModel by lazy {
        ViewModelProvider(this)[ReservationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservation_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.reservationListLiveData.observe(viewLifecycleOwner) { reservations ->
            epoxyController.reservations = reservations
            if (reservations == null) {
                Toast.makeText(requireActivity(), HomeCustomerActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }
        }
        viewModel.getRentalList()

        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    private fun onReservationSelected(reservationNumber: String) {

        // If status is pending send property to allow to still complete reservation in next step



    }
}