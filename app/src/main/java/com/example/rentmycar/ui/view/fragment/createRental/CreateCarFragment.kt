package com.example.rentmycar.ui.view.fragment.createRental

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.rentmycar.R
import com.example.rentmycar.data.room.CarRoom
import com.example.rentmycar.databinding.FragmentCreateCarBinding
import com.example.rentmycar.databinding.FragmentCreateEngineBinding
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.example.rentmycar.ui.viewmodel.CarViewModel
import com.example.rentmycar.ui.viewmodel.RentalDetails
import com.example.rentmycar.ui.viewmodel.RentalViewModel
import kotlinx.android.synthetic.main.fragment_create_car.*
import kotlinx.android.synthetic.main.fragment_create_engine.*
import kotlinx.android.synthetic.main.fragment_create_rental.*


class CreateCarFragment : Fragment() {
    private val viewModel : CarViewModel by lazy {
        ViewModelProvider(this)[CarViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.carResult.observe(viewLifecycleOwner) { carResult ->
            if (carResult == null) {
                Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }

            findNavController().navigate(R.id.create_car_to_engine)
        }
        return inflater.inflate(R.layout.fragment_create_car, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(requireActivity() is AppCompatActivity){
            (requireActivity() as AppCompatActivity).supportActionBar?.show()
        }


        car_to_engine.setOnClickListener{
            //                Car
            val constructionYear = input_cyear.editText?.text?.trim { it <= ' ' }.toString().toInt()
            val mileage = input_mileage.editText?.text?.trim { it <= ' ' }.toString().toInt()
            val model = input_model.editText?.text?.trim { it <= ' ' }.toString()

            val car = CarRoom(0, constructionYear,mileage,model)

            viewModel.saveCar(requireContext(), car)
        }
    }

}
