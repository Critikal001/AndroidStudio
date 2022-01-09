package com.example.rentmycar.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import com.example.rentmycar.data.model.api.EngineSpec
import com.example.rentmycar.data.model.api.EngineType
import com.example.rentmycar.databinding.FragmentLoginBinding

import com.example.rentmycar.databinding.RentalFragmentBinding

import com.example.rentmycar.ui.viewmodel.RentalViewModel



class RentalFragment : Fragment() {
    private lateinit var rentalViewModel: RentalViewModel
    private var _binding: RentalFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        rentalViewModel.todoResponse.observe(this) {
            binding.result.text = rentalViewModel.todoResponse.value
        }

        binding.get.setOnClickListener {
            rentalViewModel.getEngineSpec()
        }

//        binding.delete.setOnClickListener {
//            rentalViewModel.deleteTodoItem(1)
//        }

        // todo: introduce Moshi converter
        binding.post.setOnClickListener {
            val engineSpec = EngineSpec(engineSpecId = "1", EngineType.BEVEngine, fuelType = "bezine", fuelPrice = 123.0, fuelUsePerKm = 123.0, costEngineType = 178.0,pricePerKm = 25.0)
            rentalViewModel.createEngineSpec(engineSpec)

        }
        _binding = RentalFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }
}

//    viewModel.createEngineSpec(engineSpec)
