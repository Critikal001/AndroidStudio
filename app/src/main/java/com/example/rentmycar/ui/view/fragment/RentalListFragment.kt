package com.example.rentmycar.ui.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rentmycar.R
import com.example.rentmycar.ui.viewmodel.RentalViewModel


class RentalListFragment : Fragment() {

    companion object {
        fun newInstance() = RentalListFragment()
    }

    private lateinit var viewModel: RentalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rental_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RentalViewModel::class.java)
        // TODO: Use the ViewModel
    }

}