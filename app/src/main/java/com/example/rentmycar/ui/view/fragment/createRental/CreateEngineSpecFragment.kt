package com.example.rentmycar.ui.view.fragment.createRental

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.EngineType
import com.example.rentmycar.databinding.FragmentCreateEngineSpecBinding
import com.example.rentmycar.databinding.FragmentCreateRentalBinding
import com.example.rentmycar.ui.viewmodel.RentalDetails
import kotlinx.android.synthetic.main.fragment_create_engine_spec.*
import kotlinx.android.synthetic.main.fragment_create_rental.*


class CreateEngineSpecFragment : Fragment() {
//    private var token : String = ""
//    private var userID : String = ""
//    private val rentalDetails : RentalDetails by lazy {
//        ViewModelProvider(this)[RentalDetails::class.java] }
//    private var _binding: FragmentCreateEngineSpecBinding? = null
//    private val binding get() = _binding!!
//    private var engineType :EngineType = EngineType.BEVEngine
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        engine_type.setOnCheckedChangeListener { group, checkedId ->
//            engineType = when(checkedId){
//                R.id.bev_engine -> EngineType.BEVEngine
//                R.id.fcev_engine -> EngineType.FCEVEngine
//                R.id.ice_engine -> EngineType.ICEEngine
//
//                else -> EngineType.BEVEngine
//            }
//        }
//        _binding = FragmentCreateEngineSpecBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        if(requireActivity() is AppCompatActivity){
//            (requireActivity() as AppCompatActivity).supportActionBar?.show()
//        }
//
//
//        binding.toRentalOverview.setOnClickListener{
//            rentalDetails.engineType = engineType
//            rentalDetails.fuelType = input_fuel_type.toString()
//            rentalDetails.fuelUsePerKm = input_fuel_use_per_km.toString().toDouble()
//            rentalDetails.fuelPrice = input_fuel_price.toString().toDouble()
//        }
//    }

}