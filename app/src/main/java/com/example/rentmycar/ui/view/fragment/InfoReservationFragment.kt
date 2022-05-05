package com.example.rentmycar.ui.view.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.Rental
import com.example.rentmycar.data.model.api.post.Reservation
import com.example.rentmycar.data.model.api.post.SelectedTimeSlot
import com.example.rentmycar.reservations
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.example.rentmycar.ui.view.activity.MyDrawerController
import com.example.rentmycar.ui.viewmodel.RentalViewModel
import com.example.rentmycar.ui.viewmodel.ReservationViewModel
import kotlinx.android.synthetic.main.fragment_info_reservation.*


class InfoReservationFragment : Fragment() {
    private val viewModel: ReservationViewModel by lazy {
        ViewModelProvider(this)[ReservationViewModel::class.java]  }
    private val rentalViewModel: RentalViewModel by lazy {
        ViewModelProvider(this)[RentalViewModel::class.java]  }
    private lateinit var selectedTimeSlot : SelectedTimeSlot
    private val safeArgs: InfoReservationFragmentArgs by navArgs()

    private var myDrawerController: MyDrawerController? = null
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        myDrawerController = try {
            activity as MyDrawerController
        } catch (e: ClassCastException) {
            throw ClassCastException("$activity must implement MyDrawerController")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myDrawerController?.setDrawer_Locked();

        return inflater.inflate(R.layout.fragment_info_reservation, container, false)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        //myDrawerController?.setDrawer_UnLocked()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rentalViewModel.rentalDetailResult.observe(viewLifecycleOwner){ rental ->

            if (rental == null){
                Toast.makeText(requireActivity(), HomeCustomerActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }
            rental.selectedSlots?.forEach { selectedSlot ->
                if (selectedSlot.id == safeArgs.selectedSlotId) {
                    date.text = selectedSlot.date
                    rental_name.text = rental.name
                    time.text = selectedSlot.timeSlot!!?.startAt
                    selectedTimeSlot = selectedSlot
                }
            }
            priceTextViewI.text = rental.price


            confirm.setOnClickListener {
                var reservation = Reservation(
                    1,
                    selectedTimeSlot,
                    rental,
                    null
                )

                if (reservation != null) {
                    viewModel.createReservation(reservation)
                }
            }

        }
        annul.setOnClickListener {
            findNavController().navigate(R.id.carListFragment)
        }

        viewModel.reservationLiveData.observe(viewLifecycleOwner) {reservation ->
            if (reservation == null){
                Toast.makeText(requireActivity(), HomeCustomerActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }
            findNavController().navigate(R.id.action_reservationCreateFragment_to_reservationPaymentFragment)

        }

        rentalViewModel.getRentalById(safeArgs.rentalId)


    }

}