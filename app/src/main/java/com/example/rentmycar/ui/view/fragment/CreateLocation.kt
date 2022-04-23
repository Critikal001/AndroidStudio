package com.example.rentmycar.ui.view.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rentmycar.R
import com.example.rentmycar.data.room.LocationRoom
import com.example.rentmycar.ui.view.activity.HomeProviderActivity

import com.example.rentmycar.ui.viewmodel.LocationViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_create_location.*
import java.io.IOException
import java.util.*

class CreateLocation : Fragment(), OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var address: Address? = null
    var mMap: GoogleMap? = null
    private val geoCoder = Geocoder(HomeProviderActivity.context, Locale.getDefault())
    private val viewModel: LocationViewModel by lazy {
        ViewModelProvider(this)[LocationViewModel::class.java]
    }
    private val safeArgs: CreateLocationArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_create_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.locationResult.observe(viewLifecycleOwner) { locationResult ->
            if (locationResult == null) {
                Toast.makeText(requireActivity(), HomeProviderActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }

            val directions = CreateLocationDirections.createLocationToRentalOverview(rentalId = safeArgs.rentalId, carId = safeArgs.carId, rentalPlanId = safeArgs.rentalPlanId,locationId = locationResult.toInt())
            findNavController().navigate(directions)
        }


        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        search_location.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchLocation(view)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        btn_set_location.setOnClickListener {
            if (address != null) {
                val location: LocationRoom = parseAddress(address!!)
                viewModel.saveLocation(requireContext(), location)

            }
        }
    }

    private fun parseAddress(address: Address): LocationRoom {

        return LocationRoom(
            id = 0,
            address.thoroughfare,
            address.featureName,
            address.postalCode,
            address.locality,
            address.countryName,
            address.latitude,
            address.longitude,


            )
    }


    fun searchLocation(view: View) {
        val location: String = search_location.query.toString().trim()
        var addressList: List<Address>? = null

        if (location == "") {
            Toast.makeText(HomeProviderActivity.context,
                HomeProviderActivity.context.getString(R.string.no_search_query),
                Toast.LENGTH_SHORT).show()
        } else {
            try {
                addressList = geoCoder.getFromLocationName(location, 1)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            mMap?.clear()
            if (addressList == null || addressList.isEmpty()) {
                Toast.makeText(context,
                    HomeProviderActivity.context.getString(R.string.no_results_found),
                    Toast.LENGTH_LONG).show()
            } else {
                address = addressList[0]
                val latLng = LatLng(address!!.latitude, address!!.longitude)
                val addressLine = address!!.getAddressLine(0).toString()
                mMap?.addMarker(
                    MarkerOptions().position(latLng).title(addressLine))?.showInfoWindow()
                mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }

        }
    }

    // Permission alreeady checked in onViewCreated()
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        checkPermissions()
        mMap = googleMap
        googleMap.isMyLocationEnabled = true
        with(mMap!!.uiSettings) {
            isZoomControlsEnabled = true
            isMyLocationButtonEnabled = true
        }
        googleMap.setOnMapClickListener(this)
    }

    override fun onMapClick(position: LatLng) {
        // Add marker by clicking on map
        mMap?.clear()
        var addressList: List<Address>? = null

        try {
            addressList = geoCoder.getFromLocation(position.latitude, position.longitude, 1)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        address = addressList!![0]
        val addressLine = address!!.getAddressLine(0).toString()
        mMap?.addMarker(
            MarkerOptions().position(position).title(addressLine))?.showInfoWindow()
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15f))
    }


    private fun checkPermissions() {
        // Check permissions and if none are found ask for them
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), 101)
        }
    }
}