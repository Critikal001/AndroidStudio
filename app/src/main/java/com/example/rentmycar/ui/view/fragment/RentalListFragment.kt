package com.example.rentmycar.ui.view.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rentmycar.R
import com.example.rentmycar.data.room.RoomService
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.example.rentmycar.ui.view.activity.MyDrawerController
import com.example.rentmycar.ui.viewmodel.RentalViewModel
import com.example.rentmycar.utils.sharedPrefFile


class RentalListFragment : Fragment() {
    private lateinit var viewModel: RentalViewModel
    private var myDrawerController: MyDrawerController? = null
    private val TAG = "_listeVehiculeFragment"
    private var token : String = ""
    private var userID : String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myDrawerController = try {
            activity as MyDrawerController
        } catch (e: ClassCastException) {
            throw ClassCastException("$activity must implement MyDrawerController")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPref = RoomService.context.getSharedPreferences(
            sharedPrefFile, Context.MODE_PRIVATE
        )
        return inflater.inflate(R.layout.rental_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RentalViewModel::class.java)
        // TODO: Use the ViewModel
    }

}