package com.example.rentmycar.ui.view.fragment.createRental

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.*
import com.example.rentmycar.data.room.RentalRoom
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.example.rentmycar.ui.viewmodel.RentalViewModel
import kotlinx.android.synthetic.main.fragment_create_car.*

import kotlinx.android.synthetic.main.fragment_create_rental.*

class CreateRentalFragment : Fragment() {
        private val viewModel : RentalViewModel by lazy {
            ViewModelProvider(this)[RentalViewModel::class.java] }



        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            viewModel.rentalResult.observe(viewLifecycleOwner) { rentalResult ->
                if (rentalResult == null) {
                    Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                    return@observe
                }
                val directions = CreateRentalFragmentDirections.createRentalToCar(rentalId = rentalResult.toInt())
                findNavController().navigate(directions)
            }

            return inflater.inflate(R.layout.fragment_create_rental, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            if(requireActivity() is AppCompatActivity){
                (requireActivity() as AppCompatActivity).supportActionBar?.show()
            }
//


            rental_to_car.setOnClickListener{
//                Rental
                val rentalName = input_rental_name.editText?.text?.trim { it <= ' ' }.toString()
                val mileageRental = input_mileage_rental.editText?.text?.trim { it <= ' ' }.toString().toInt()
                val price = input_price.editText?.text?.trim { it <= ' ' }.toString()

                val rental = RentalRoom(0, rentalName, price, mileageRental)

                viewModel.saveRental(requireContext(), rental)
            }
        }

    }
