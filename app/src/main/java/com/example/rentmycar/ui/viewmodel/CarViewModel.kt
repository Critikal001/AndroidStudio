package com.example.rentmycar.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmycar.data.api.request.CarRequest
import com.example.rentmycar.data.model.api.post.Car
import com.example.rentmycar.data.repositories.CarRepository
import com.example.rentmycar.data.room.CarRoom

import kotlinx.coroutines.launch

class CarViewModel :ViewModel() {


    private val _carListLiveData = MutableLiveData<List<CarRequest>?>()
    val carListLiveData: LiveData<List<CarRequest>?> = _carListLiveData

    private val _carResult = MutableLiveData<Int>()
    val carResult: LiveData<Int> get() = _carResult

    private val _carRoomLiveData = MutableLiveData<CarRoom?>()
    val carRoomLiveData: LiveData<CarRoom?> = _carRoomLiveData

    private val _carCreateResult = MutableLiveData<CarRequest?>()
    val carResourceResult: LiveData<CarRequest?> = _carCreateResult






    fun saveCar(context: Context, carRoom: CarRoom) {
        viewModelScope.launch {
            val response = CarRepository.saveCar(context, carRoom)
            _carResult.value = response.toInt()
        }
    }

    fun getCar(context: Context, carId: Int) {
        viewModelScope.launch {
            val response = CarRepository.getCar(context, carId)
            _carRoomLiveData.postValue(response)
        }
    }

    fun createCar(car: Car) {
        viewModelScope.launch {
            val response = CarRepository.createCar(car){
                _carCreateResult.postValue(it?.body())
            }

        }
    }
}