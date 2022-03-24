package com.example.rentmycar.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmycar.data.api.request.LocationRequest
import com.example.rentmycar.data.model.api.post.Location
import com.example.rentmycar.data.repositories.LocationRepository

import com.example.rentmycar.data.room.LocationRoom
import kotlinx.coroutines.launch

class LocationViewModel :ViewModel() {

    private val _locationListLiveData = MutableLiveData<List<LocationRequest>?>()
    val locationListLiveData: LiveData<List<LocationRequest>?> = _locationListLiveData

    private val _locationResult = MutableLiveData<Int>()
    val locationResult: LiveData<Int> get() = _locationResult

    private val _locationRoomLiveData = MutableLiveData<LocationRoom?>()
    val locationRoomLiveData: LiveData<LocationRoom?> = _locationRoomLiveData

    private val _locationCreateResult = MutableLiveData<LocationRequest?>()
    val locationResourceResult: LiveData<LocationRequest?> = _locationCreateResult



    fun getLocationList(id: Int) {
        viewModelScope.launch {
            val response = LocationRepository.getLocationList()
            _locationListLiveData.postValue(response)
        }
    }



    fun saveLocation(context: Context, locationRoom: LocationRoom) {
        viewModelScope.launch {
            val response = LocationRepository.saveLocation(context, locationRoom)
            _locationResult.value = response.toInt()
        }
    }

    fun getLocation(context: Context, locationId: Int) {
        viewModelScope.launch {
            val response = LocationRepository.getLocation(context, locationId)
            _locationRoomLiveData.postValue(response)
        }
    }

    fun createLocation(location: Location) {
        viewModelScope.launch {
            LocationRepository.createLocation(location){
                _locationCreateResult.postValue(it?.body())

            }
        }
    }
}