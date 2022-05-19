package com.example.rentmycar.ui.viewmodel
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.example.rentmycar.data.model.api.post.*
import com.example.rentmycar.data.repositories.RentalRepository

import com.example.rentmycar.data.room.RentalRoom
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class RentalViewModel : ViewModel() {

    private val _rentalListLiveData = MutableLiveData<List<Rental?>>()
    val rentalListLiveData: LiveData<List<Rental?>> = _rentalListLiveData

    private val _rentalResult = MutableLiveData<Int>()
    val rentalResult: LiveData<Int> get() = _rentalResult

    private val _rentalRoomLiveData = MutableLiveData<RentalRoom?>()
    val rentalRoomLiveData: LiveData<RentalRoom?> = _rentalRoomLiveData

    private val _rentalCreateResult = MutableLiveData<Rental?>()
    val rentalCreateResult: LiveData<Rental?> = _rentalCreateResult

    private val _rentalDetailResult = MutableLiveData<Rental?>()
    val rentalDetailResult: LiveData<Rental?> = _rentalDetailResult

    private val _rentalImageResult = MutableLiveData<Images>()
    val rentalImageResult: LiveData<Images> = _rentalImageResult



    fun getRentalList(context: Context) {
        viewModelScope.launch {
            RentalRepository.getRentalList(context){
            _rentalListLiveData.postValue(it?.body())
            }
        }
    }



    fun saveRental(context: Context, rentalRoom: RentalRoom) {
        viewModelScope.launch {
            val response = RentalRepository.saveRental(context, rentalRoom)
            _rentalResult.value = response.toInt()
        }
    }

    fun getRental(context: Context, rentalId: Int) {
        viewModelScope.launch {
            val response = RentalRepository.getRental(context, rentalId)
            _rentalRoomLiveData.postValue(response)
        }
    }

    fun createRental(rental: Rental) {
        viewModelScope.launch {
            RentalRepository.createRental(rental){
                _rentalCreateResult.postValue(it?.body())
            }

        }
    }

    fun getRentalById(rentalId: Int){
        viewModelScope.launch{
            RentalRepository.getRentalById(rentalId){
                _rentalDetailResult.postValue(it?.body())
           }
        }
    }

    fun uploadImage(id : Int,image: MultipartBody.Part) {
        viewModelScope.launch {
            RentalRepository.uploadImage(id, image){
                _rentalImageResult.postValue(it?.body())
            }
        }
    }
}