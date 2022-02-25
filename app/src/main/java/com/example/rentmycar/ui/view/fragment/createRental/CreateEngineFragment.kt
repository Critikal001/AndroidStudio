package com.example.rentmycar.ui.view.fragment.createRental

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rentmycar.R
import com.example.rentmycar.data.room.EngineRoom
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.example.rentmycar.ui.viewmodel.CarViewModel
import com.example.rentmycar.ui.viewmodel.EngineViewModel

import com.example.rentmycar.ui.viewmodel.RentalDetails
import com.example.rentmycar.ui.viewmodel.RentalViewModel
import kotlinx.android.synthetic.main.fragment_create_engine.*
import kotlinx.android.synthetic.main.fragment_create_rental.*


/**
 * A simple [Fragment] subclass.
 * Use the [CreateEngineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateEngineFragment : Fragment() {
    private val viewModel : EngineViewModel by lazy {
        ViewModelProvider(this)[EngineViewModel::class.java] }
    private val safeArgs: CreateEngineFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.engineResult.observe(viewLifecycleOwner) { engineResult ->
            if (engineResult == null) {
                Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }

            val directions = CreateEngineFragmentDirections.createEngineToEnginespec(rentalId = safeArgs.rentalId, carId = safeArgs.carId, engineId = engineResult.toInt())
            findNavController().navigate(directions)
        }

        return inflater.inflate(R.layout.fragment_create_engine, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(requireActivity() is AppCompatActivity){
            (requireActivity() as AppCompatActivity).supportActionBar?.show()
        }


        engine_to_enginespec.setOnClickListener{
//                Engine
            val power = input_power.editText?.text?.trim { it <= ' ' }.toString().replace(",",".").toDouble()
            val engine = EngineRoom(0, power)

            viewModel.saveEngine(requireContext(), engine)

        }
    }

}