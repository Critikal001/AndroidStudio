package com.example.rentmycar.ui.view.fragment.createRental

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.rentmycar.R
import com.example.rentmycar.databinding.FragmentCreateCarBinding
import com.example.rentmycar.databinding.FragmentCreateEngineBinding
import com.example.rentmycar.ui.viewmodel.RentalDetails
import kotlinx.android.synthetic.main.fragment_create_car.*
import kotlinx.android.synthetic.main.fragment_create_engine.*


class CreateCarFragment : Fragment() {
    private var token : String = ""
    private var userID : String = ""
    private val rentalDetails : RentalDetails by lazy {
        ViewModelProvider(this)[RentalDetails::class.java] }
    private var _binding: FragmentCreateCarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCreateCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(requireActivity() is AppCompatActivity){
            (requireActivity() as AppCompatActivity).supportActionBar?.show()
        }


//        binding.toDetailsEngine.setOnClickListener{
//            rentalDetails.constructionYear = input_cyear.toString().toInt()
//            rentalDetails.mileage = input_mileage.toString().toInt()
//            rentalDetails.model = input_power.toString()
//            binding.toDetailsEngine.findNavController().navigate(R.id.create_rental_to_car)
//        }
    }

}
