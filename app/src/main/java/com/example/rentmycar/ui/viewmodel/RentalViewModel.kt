package com.example.rentmycar.ui.viewmodel
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmycar.data.api.request.RentalRequest

import com.example.rentmycar.data.model.api.post.*
import com.example.rentmycar.data.repositories.RentalRepository

import com.example.rentmycar.data.room.RentalRoom
import kotlinx.coroutines.launch

class RentalViewModel : ViewModel() {
    private val rentalRepository = RentalRepository()

    private val _rentalListLiveData = MutableLiveData<List<RentalRequest>?>()
    val rentalListLiveData: LiveData<List<RentalRequest>?> = _rentalListLiveData

    private val _rentalResult = MutableLiveData<Int>()
    val rentalResult: LiveData<Int> get() = _rentalResult

    private val _rentalRoomLiveData = MutableLiveData<RentalRoom?>()
    val rentalRoomLiveData: LiveData<RentalRoom?> = _rentalRoomLiveData

    private val _rentalCreateResult = MutableLiveData<RentalRequest?>()
    val rentalResourceResult: LiveData<RentalRequest?> = _rentalCreateResult



    fun getRentalList() {
        viewModelScope.launch {
            val response = rentalRepository.getRentalList()
            _rentalListLiveData.postValue(response)
        }
    }



    fun saveRental(context: Context, rentalRoom: RentalRoom) {
        viewModelScope.launch {
            val response = rentalRepository.saveRental(context, rentalRoom)
            _rentalResult.value = response.toInt()
        }
    }

    fun getRental(context: Context, rentalId: Int) {
        viewModelScope.launch {
            val response = rentalRepository.getRental(context, rentalId)
            _rentalRoomLiveData.postValue(response)
        }
    }

    fun createRental(rental: Rental) {
        viewModelScope.launch {
            val response = rentalRepository.createRental(rental)
            _rentalCreateResult.postValue(response)
        }
    }
}