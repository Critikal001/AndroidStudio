package com.example.rentmycar.ui.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rentmycar.R
import com.example.rentmycar.ui.controllers.RentalListController
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.example.rentmycar.ui.viewmodel.RentalViewModel

import kotlinx.android.synthetic.main.rental_list_fragment.*


class RentalListFragment : Fragment() {
    private  val viewModel: RentalViewModel by lazy {
        ViewModelProvider(this)[RentalViewModel::class.java]  }
    private val controller = RentalListController(::onCarSelected)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.rental_list_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.rentalListLiveData.observe(viewLifecycleOwner){ rentals ->
            controller.rentals = rentals
            if (rentals == null){
                Toast.makeText(requireActivity(), HomeCustomerActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }

        }

        viewModel.getRentalList(requireContext())
        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerViewList)
        epoxyRecyclerView.setControllerAndBuildModels(controller)
       // backButtonRentalList.setOnClickListener { this.findNavController().navigate(R.id.action_ListeVehiculeFragment_pop) }


    }

    private fun onCarSelected(id: Int) {
        val directions =
            RentalListFragmentDirections.carListFragmentToCarDetailFragment(id)
        findNavController().navigate(directions)
    }


}