package com.example.rentmycar.ui.view.fragment.createRental

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.EngineType
import com.example.rentmycar.data.room.EngineSpecRoom
import com.example.rentmycar.ui.view.activity.HomeProviderActivity

import com.example.rentmycar.ui.viewmodel.RentalDetails
import com.example.rentmycar.ui.viewmodel.RentalViewModel
import com.example.rentmyengineSpec.ui.viewmodel.EngineSpecViewModel
import kotlinx.android.synthetic.main.fragment_create_engine_spec.*
import kotlinx.android.synthetic.main.fragment_create_rental.*


class CreateEngineSpecFragment : Fragment() {
    private  val viewModel: EngineSpecViewModel by lazy {
        ViewModelProvider(this)[EngineSpecViewModel::class.java]  }
    private val safeArgs: CreateEngineSpecFragmentArgs by navArgs()


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


        viewModel.engineSpecResult.observe(viewLifecycleOwner) { engineSpecResult ->
            if (engineSpecResult == null) {
                Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }

            val directions = CreateEngineSpecFragmentDirections.createEnginespecToCreateLocation(rentalId = safeArgs.rentalId, carId = safeArgs.carId, engineId = safeArgs.engineId, engineSpecId = engineSpecResult.toInt())
            findNavController().navigate(directions)
        }


        return inflater.inflate(R.layout.fragment_create_engine_spec, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        if(requireActivity() is AppCompatActivity){
            (requireActivity() as AppCompatActivity).supportActionBar?.show()

            enginespec_to_location.setOnClickListener {
                val inputEngineType = autoCompleteTextView.text.toString()
                val fuelType = input_fuel_type.editText?.text?.trim { it <= ' ' }.toString()
                val fuelUsePerKm = input_fuel_use_per_km.editText?.text?.trim { it <= ' ' }.toString().replace(",",".").toDouble()
                val fuelPrice = input_fuel_price.editText?.text?.trim { it <= ' ' }.toString().replace(",",".").toDouble()

                val engineType = getCarType(inputEngineType)

                val engineSpec = EngineSpecRoom(0, engineType, fuelType,fuelUsePerKm,fuelPrice)

                viewModel.saveEngineSpec(requireContext(), engineSpec)
            }

        }
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