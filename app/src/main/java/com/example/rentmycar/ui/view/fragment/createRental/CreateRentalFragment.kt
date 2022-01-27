package com.example.rentmycar.ui.view.fragment.createRental

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.*
import androidx.navigation.fragment.navArgs
import com.example.rentmycar.ui.viewmodel.RentalDetails
import com.example.rentmycar.ui.viewmodel.RentalViewModel
import kotlinx.android.synthetic.main.fragment_create_car.*
import kotlinx.android.synthetic.main.fragment_create_engine.*
import kotlinx.android.synthetic.main.fragment_create_rental.*

class CreateRentalFragment : Fragment() {
        private lateinit var engineType : EngineType

        private val viewmodel : RentalViewModel by lazy {
            ViewModelProvider(this)[RentalViewModel::class.java] }



        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {



            return inflater.inflate(R.layout.fragment_create_rental, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            if(requireActivity() is AppCompatActivity){
                (requireActivity() as AppCompatActivity).supportActionBar?.show()
            }
//            engine_type.setOnCheckedChangeListener { group, checkedId ->
//                engineType = when(checkedId) {
//                    R.id.bev_engine -> EngineType.BEVEngine
//                    R.id.fcev_engine -> EngineType.FCEVEngine
//                    R.id.ice_engine -> EngineType.ICEEngine
//
//                    else -> EngineType.BEVEngine
//                }
//            }


            btn_next.setOnClickListener{
//                Rental
                val rentalName = input_rental_name.editText?.text?.trim { it <= ' ' }.toString()
                val mileageRental = input_mileage_rental.editText?.text?.trim { it <= ' ' }.toString().toInt()
                val price = input_price.editText?.text?.trim { it <= ' ' }.toString()
//                Car
                val constructionYear = input_cyear.editText?.text?.trim { it <= ' ' }.toString().toInt()
                val mileage = input_mileage.editText?.text?.trim { it <= ' ' }.toString().toInt()
                val model = input_power.editText?.text?.trim { it <= ' ' }.toString()
//                Engine
                val power = input_power.editText?.text?.trim { it <= ' ' }.toString().replace(",",".").toDouble()
//                EngineSpec
                val engineType = EngineType.BEVEngine
                val fuelType = input_fuel_type.editText?.text?.trim { it <= ' ' }.toString()
                val fuelUsePerKm = input_fuel_use_per_km.editText?.text?.trim { it <= ' ' }.toString().replace(",",".").toDouble()
                val fuelPrice = input_fuel_price.editText?.text?.trim { it <= ' ' }.toString().replace(",",".").toDouble()


                val engineSpec = EngineSpec(engineType, fuelType, fuelUsePerKm, fuelPrice, 0.0, 0.0 )
                val engine = Engine(power, engineSpec)
                val car = Car(constructionYear, mileage, model, engine)
                val location = Location("", "", 0.0, 0.0)
                val user = User("pep","vliet", "bart")
                val provider = Provider(user)
                val rental = Rental(rentalName,price, mileageRental, false, car, location, provider)


                viewmodel.createRental(requireContext(),engineSpec,engine,car,location,user,provider, rental)

                btn_next.findNavController().navigate(R.id.create_rental_to_car)

//                finish_create_rental.findNavController().navigate(R.id.create_rental_to_car)

            }

//            binding.toDetailsEngine.setOnClickListener{ Car

//            }
//            engine
//            rentalDetails.power = input_power.toString().toDoubleOrNull()!!
//            binding.toDetailsEngineSpec.findNavController().navigate(R.id.create_engine_to_enginespec)
//            enginespec
//            engine_type.setOnCheckedChangeListener { group, checkedId ->
//            engineType = when(checkedId){
//                R.id.bev_engine -> EngineType.BEVEngine
//                R.id.fcev_engine -> EngineType.FCEVEngine
//                R.id.ice_engine -> EngineType.ICEEngine
//
//                else -> EngineType.BEVEngine
            //        binding.toRentalOverview.setOnClickListener{
//            rentalDetails.engineType = engineType
//            rentalDetails.fuelType = input_fuel_type.toString()
//            rentalDetails.fuelUsePerKm = input_fuel_use_per_km.toString().toDouble()
//            rentalDetails.fuelPrice = input_fuel_price.toString().toDouble()
//        }
//            }
//        }
        }

    }
