package com.example.rentmycar.ui.view.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rentmycar.R
import com.example.rentmycar.databinding.RentalListFragmentBinding
import com.example.rentmycar.ui.adapter.RentalListAdapter
import com.example.rentmycar.ui.view.activity.MyDrawerController
import com.example.rentmycar.ui.viewmodel.RentalDetails
import com.example.rentmycar.ui.viewmodel.RentalViewModel
import com.example.rentmycar.ui.viewmodel.ReservationDetails
import com.example.rentmycar.utils.sharedPrefFile


class RentalListFragment : Fragment() {
    private  val viewModel: RentalViewModel by lazy {
        ViewModelProvider(this)[RentalViewModel::class.java]  }
    private var myDrawerController: MyDrawerController? = null
    private var token : String = ""
    private var userID : String = ""
    private var _binding: RentalListFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_car, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        binding.recyclerViewRental.layoutManager = LinearLayoutManager(requireActivity())
    }

//    fun checkList() {
//        val data = viewModel.getRentalList().
//
//        val vm = ViewModelProvider(requireActivity())[RentalDetails::class.java]
//        val vmRes = ViewModelProvider(requireActivity())[ReservationDetails::class.java]
//        binding.recyclerViewRental.adapter = RentalListAdapter(requireActivity(), data, vm, vmRes)
//    }

}