package com.example.rentmycar.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmycar.data.model.api.post.Rental
import com.example.rentmycar.data.model.api.post.SelectedTimeSlot
import com.example.rentmycar.data.repositories.AvailabilityRepository
import com.example.rentmycar.data.repositories.RentalRepository
import kotlinx.coroutines.launch

class AvailabilityViewModel(
    id: Int
): ViewModel() {
    var rentalId = id

    private val _timeslotLiveData = MutableLiveData<SelectedTimeSlot>()
    val timeslotLiveData: LiveData<SelectedTimeSlot> = _timeslotLiveData

    private val _rentalDetailResult = MutableLiveData<Rental>()
    val rentalDetailResult: LiveData<Rental> = _rentalDetailResult




    fun createTimeslots(timeSlots: List<SelectedTimeSlot>) {
        viewModelScope.launch {
            AvailabilityRepository.createTimeSlots(rentalId, timeSlots){
                _timeslotLiveData.postValue(it?.body())
            }

        }
    }

    fun getRentalById(){
        viewModelScope.launch{
            RentalRepository.getRentalById(rentalId){
                _rentalDetailResult.postValue(it?.body())
            }
        }
    }


}