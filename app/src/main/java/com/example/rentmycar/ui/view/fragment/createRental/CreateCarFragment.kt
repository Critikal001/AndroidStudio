package com.example.rentmycar.ui.view.fragment.createRental

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.EngineType
import com.example.rentmycar.data.room.CarRoom
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.example.rentmycar.ui.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.fragment_create_car.*
import kotlinx.android.synthetic.main.fragment_create_car.autoCompleteTextView

import kotlinx.android.synthetic.main.fragment_create_rental.*
import kotlinx.android.synthetic.main.fragment_sign_up.*


class CreateCarFragment : Fragment() {
    private val viewModel : CarViewModel by lazy {
        ViewModelProvider(this)[CarViewModel::class.java] }
    private val safeArgs: CreateCarFragmentArgs by navArgs()


    override fun onResume() {
        super.onResume()
        val carTypes = resources.getStringArray(R.array.car_types)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.model_dropdown_list_item, carTypes)
        autoCompleteTextView.setAdapter(arrayAdapter)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.carResult.observe(viewLifecycleOwner) { carResult ->
            if (carResult == null) {
                Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }

            val directions = CreateCarFragmentDirections.createCarToEngine(rentalId = safeArgs.rentalId, carId = carResult.toInt())
            findNavController().navigate(directions)
        }
        return inflater.inflate(R.layout.fragment_create_car, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(requireActivity() is AppCompatActivity){
            (requireActivity() as AppCompatActivity).supportActionBar?.show()
        }


        car_to_engine.setOnClickListener{
            if (validateInputs()) {
                //                Car
                val constructionYear = input_cyear.text.toString().toInt()
                val mileage = input_mileage.text.toString().toInt()
                val model = input_model.text.toString()
                val power = input_power.text.toString().toDouble()
                val inputEngineType = autoCompleteTextView.text.toString()
                val fuelType = input_fuel_type.text.toString()
                val fuelUsePerKm = input_fuel_use_per_km.text.toString().toDouble()
                val fuelPrice = input_fuel_price.text.toString().toDouble()

                val engineType = getCarType(inputEngineType)
                val car = CarRoom(0, constructionYear, mileage, model,power, engineType, fuelType,fuelUsePerKm,fuelPrice)

                viewModel.saveCar(requireContext(), car)
            }
        }
    }

    fun validateInputs(): Boolean {

        if (input_cyear?.text.isNullOrEmpty()) {
            input_cyear?.error = "Construction year cannot be empty."
            return false
        }
        if (input_mileage?.text.isNullOrEmpty()) {
            input_mileage?.error = "Mileage cannot be empty."
            return false
        }
        if (input_model?.text.isNullOrEmpty()) {
            input_model?.error = "Model cannot be empty."
            return false
        }
        if (input_power?.text.isNullOrEmpty()) {
            input_power?.error = "Power cannot be empty."
            return false
        }
        if (input_fuel_type?.text.isNullOrEmpty()) {
            input_fuel_type?.error = "Fuel type cannot be empty."
            return false
        }
        if (input_fuel_use_per_km?.text.isNullOrEmpty()) {
            input_fuel_use_per_km?.error = "Fuel use cannot be empty."
            return false
        }
        if (input_fuel_price?.text.isNullOrEmpty()) {
            input_fuel_price?.error = "Fuel price cannot be empty."
            return false
        }
        return true
    }

    private fun getCarType(carType: String): EngineType {
        // Map carType selection to correct ENUM type
        return when (true) {
            carType.contains("ICE") -> {
                EngineType.ICEEngine
            }
            carType.contains("FCEV") -> {
                EngineType.FCEVEngine
            }
            else -> {
                EngineType.BEVEngine
            }
        }
    }

}
