package com.example.rentmycar.ui.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rentmycar.data.model.api.EngineSpec
import com.example.rentmycar.data.model.api.EngineType


import com.example.rentmycar.databinding.RentalFragmentBinding

import com.example.rentmycar.ui.viewmodel.RentalViewModel

class RentalFragment : Fragment() {

    private lateinit var binding: RentalFragmentBinding

    // This property is only valid between onCreateView and
    // onDestroyView.



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RentalFragmentBinding.inflate(inflater, container, false)
        binding= RentalFragmentBinding.inflate(layoutInflater)



        val model: RentalViewModel by viewModels()
        model.todoResponse.observe(this) {
            binding.result.text = model.todoResponse.value
        }
//
        binding.get.setOnClickListener {
            model.getEngineSpec()
        }
//
////        binding.delete.setOnClickListener {
////            rentalViewModel.deleteTodoItem(1)
////        }
//
//        // todo: introduce Moshi converter
        binding.post.setOnClickListener {
            val engineSpec = EngineSpec(engineSpecId = "11", EngineType.BEVEngine, fuelType = "bezine", fuelUsePerKm = 32.0,fuelPrice = 123.0,  costEngineType = 178.0, pricePerKm = 25.0)
            model.createEngineSpec(engineSpec)

        }

        return binding.root

    }
}

//    viewModel.createEngineSpec(engineSpec)
