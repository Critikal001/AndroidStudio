package com.example.rentmycar.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmycar.data.model.api.post.Rental
import com.example.rentmycar.data.model.api.post.Reservation
import com.example.rentmycar.data.repositories.RentalRepository
import com.example.rentmycar.data.repositories.ReservationRepository
import kotlinx.coroutines.launch

class ReservationViewModel : ViewModel(){

    private val _reservationLiveData = MutableLiveData<Reservation?>()
    val reservationLiveData: LiveData<Reservation?> = _reservationLiveData

    private val _reservationListLiveData = MutableLiveData<List<Reservation?>>()
    val reservationListLiveData: LiveData<List<Reservation?>> = _reservationListLiveData

    fun createReservation(reservation: Reservation) {
        viewModelScope.launch {
            ReservationRepository.postReservation(reservation){
                _reservationLiveData.postValue(it?.body())
            }

        }
    }


    fun getRentalList() {
        viewModelScope.launch {
            ReservationRepository.getReservationList(){
                _reservationListLiveData.postValue(it?.body())
            }
        }
    }
}