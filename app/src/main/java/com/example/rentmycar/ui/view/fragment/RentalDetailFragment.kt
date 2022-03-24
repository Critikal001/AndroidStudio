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
import com.example.rentmycar.ui.controllers.RentalDetailFragmentController
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.example.rentmycar.ui.viewmodel.RentalViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.rental_detail_fragment.*

class RentalDetailFragment : Fragment() {
    private val viewModel: RentalViewModel by lazy {
        ViewModelProvider(this)[RentalViewModel::class.java]
    }
    private val controller = RentalDetailFragmentController(::onLocationBtnClicked,
        ::onEditLocationBtnClicked, ::onEditCarBtnClicked, ::onBookNowBtnClicked)
    private val safeArgs: RentalDetailFragmentArgs by navArgs()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rental_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel
            viewModel.rentalDetailResult.observe(viewLifecycleOwner) { rental ->
            controller.rental = rental
            if (rental == null) {
                Toast.makeText(requireActivity(), HomeCustomerActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }

//            // Only show edit buttons if car belongs to logged in user
//            if (userId == car.userId) {
                btnAddResource.visibility = View.VISIBLE

        }

        val rentalId = safeArgs.rentalId.toInt()
        viewModel.getRentalById(rentalId = rentalId)

        // Upload image to car and set request code 101
        btnAddResource.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .start(101)
        }

        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(controller)


    }

    private fun onLocationBtnClicked(id: Int) {
        val directions =
            RentalDetailFragmentDirections.actionCarDetailsFragmentToLocationDetailsFragment(id)
        findNavController().navigate(directions)
    }

    private fun onEditLocationBtnClicked(id: Int) {
        val directions =
            RentalDetailFragmentDirections.actionCarDetailsFragmentToLocationCreateFragment()
        findNavController().navigate(directions)
    }

    private fun onEditCarBtnClicked(id: Int) {
        val directions =
            RentalDetailFragmentDirections.actionCarDetailsFragmentToCarCreateFragment()
        findNavController().navigate(directions)
    }

    private fun onBookNowBtnClicked(rentalId: Int) {
        val directions =
            RentalDetailFragmentDirections.actionCarDetailsFragmentToCarAvailabilityFragment(rentalId)
        findNavController().navigate(directions)
    }

}