package com.example.rentmycar.ui.view.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rentmycar.R
import com.example.rentmycar.ui.view.activity.MyDrawerController
import kotlinx.android.synthetic.main.fragment_car_detail.*
import kotlinx.android.synthetic.main.fragment_car_detail.input_fuel_price
import kotlinx.android.synthetic.main.fragment_car_detail.input_fuel_type
import kotlinx.android.synthetic.main.fragment_car_detail.input_fuel_use_per_km
import kotlinx.android.synthetic.main.fragment_create_engine_spec.*


class CarDetailFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_car_detail, container, false)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        // myDrawerController?.setDrawer_UnLocked()
    }
    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val vm = ViewModelProvider(requireActivity())[RentalDetails::class.java]

        input_constructionYear.text = vm.constructionYear.toString()
        input_mileage.text = vm.mileage.toString()
        input_model.text = vm.model
        input_power.text = vm.power.toString()
        input_engine_type.text = vm.engineType.toString()
        input_fuel_type.text = vm.fuelType
        input_fuel_use_per_km.text = vm.fuelUsePerKm.toString()
        input_fuel_price.text = vm.fuelPrice.toString()

//        Glide.with(requireActivity()).load(vm.secureUrl).into(VehiculeImageViewD)
        reseverButtonD.setOnClickListener {
            findNavController().navigate(R.id.action_carDetailFragment_to_rentalDetailFragment)
        }
        goBackButtonDetailsVehicule.setOnClickListener{ this.findNavController().navigate(R.id.action_carDetailFragment_to_rentalListFragment)

        }

    }
}