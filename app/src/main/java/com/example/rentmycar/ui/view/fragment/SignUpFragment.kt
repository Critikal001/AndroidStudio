package com.example.rentmycar.ui.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import com.example.rentmycar.MainActivity
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.EngineSpec
import com.example.rentmycar.data.model.api.EngineType
import com.example.rentmycar.ui.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val engineSpec = EngineSpec(engineSpecId = "1", EngineType.BEVEngine, fuelType = "bezine", fuelPrice = 123.0, fuelUsePerKm = 123.0, costEngineType = 178.0,pricePerKm = 25.0)
        viewModel.createEngineSpec(engineSpec)

        return inflater.inflate(R.layout.fragment_sign_up, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        // TODO: Use the ViewModel
    }

}