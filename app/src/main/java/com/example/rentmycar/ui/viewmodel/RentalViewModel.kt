package com.example.rentmycar.ui.viewmodel
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmycar.data.model.api.post.*
import com.example.rentmycar.data.api.request.RentalRequest
import com.example.rentmycar.data.repositories.RentalRepository
import kotlinx.coroutines.launch

class RentalViewModel : ViewModel() {

    private val rentalRepository = RentalRepository()
    private val _rentalData = MutableLiveData<Rental>()
    val rentalLiveData: LiveData<Rental?> = _rentalData

    private val _carLiveData = MutableLiveData<Car>()
    val carLiveData: LiveData<Car?> = _carLiveData

    private val _engineLiveData = MutableLiveData<Engine>()
    val engineLiveData: LiveData<Engine?> = _engineLiveData

    private val _engineSpecLiveData = MutableLiveData<EngineSpec>()
    val engineSpecLiveData: LiveData<EngineSpec?> = _engineSpecLiveData

    fun  getRentalList(): List<RentalRequest>? {
        val rentaLList: List<RentalRequest>?
        val request = rentalRepository.getRentalList()
        try {
            rentaLList = request
            return rentaLList
        }
        catch (e: java.lang.Exception){
            Log.i(TAG, "Can't get the rentals because: {${e.message.toString()}")
            return request
        }
    }

    fun createRental(context: Context, engineSpec: EngineSpec, engine: Engine, car: Car, location: Location, user: User, provider: Provider, rental: Rental) {
        viewModelScope.launch {
            rentalRepository.createEngineSpec(engineSpec)
            rentalRepository.createEngine(engine)
            rentalRepository.createCar(car)
            rentalRepository.createUser(user)
            rentalRepository.createProvider(provider)
            rentalRepository.createLocation(location)
            rentalRepository.createRental(context, rental)
        }
    }







}