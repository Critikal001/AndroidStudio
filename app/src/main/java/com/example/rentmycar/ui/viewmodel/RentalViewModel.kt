package com.example.rentmycar.ui.viewmodel
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rentmycar.data.model.api.post.*
import com.example.rentmycar.data.model.api.request.GetRental
import com.example.rentmycar.data.repositories.RentalRepository

import retrofit2.Response

class RentalViewModel : ViewModel() {

    private val _rentalData = MutableLiveData<Rental>()
    val rentalLiveData: LiveData<Rental?> = _rentalData

    private val _carLiveData = MutableLiveData<Car>()
    val carLiveData: LiveData<Car?> = _carLiveData

    private val _engineLiveData = MutableLiveData<Engine>()
    val engineLiveData: LiveData<Engine?> = _engineLiveData

    private val _engineSpecLiveData = MutableLiveData<EngineSpec>()
    val engineSpecLiveData: LiveData<EngineSpec?> = _engineSpecLiveData

    fun  getRentalList(): List<GetRental> {
        val rentaLList: List<GetRental>
        val request = RentalRepository.getRentalList()
        try {
            rentaLList = request
            return rentaLList
        }
        catch (e: java.lang.Exception){
            Log.i(TAG, "Can't get the rentals because: {${e.message.toString()}")
            return request
        }
    }

    fun createRental(engineSpec: EngineSpec, engine: Engine, car: Car, location: Location, user: User, provider: Provider, rental: Rental): Rental {
        RentalRepository.createEngineSpec(engineSpec)
        RentalRepository.createEngine(engine)
        RentalRepository.createCar(car)
        RentalRepository.createUser(user)
        RentalRepository.createProvider(provider)
        RentalRepository.createLocation(location)
        return RentalRepository.createRental(rental)

    }







}