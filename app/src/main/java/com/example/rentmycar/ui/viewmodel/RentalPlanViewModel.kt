package com.example.rentmycar.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmycar.data.model.api.post.RentalPlan
import com.example.rentmycar.data.model.api.post.SelectedTimeSlot
import com.example.rentmycar.data.model.api.post.TimeSlot
import com.example.rentmycar.data.repositories.RentalPlanRepository
import kotlinx.coroutines.launch

class RentalPlanViewModel: ViewModel() {
    private val _rentalPlanByIdLiveData = MutableLiveData<com.example.rentmycar.data.room.RentalPlan?>()
    val rentalPlanByIdLiveData: LiveData<com.example.rentmycar.data.room.RentalPlan?> = _rentalPlanByIdLiveData

    private val _postRentalPlanLiveData = MutableLiveData<Int>()
    val postRentalPlanLiveData: LiveData<Int?> = _postRentalPlanLiveData

    private val _rentalPlanCreateLiveData = MutableLiveData<RentalPlan?>()
    val rentalPlanCreateLiveData: LiveData<RentalPlan?> = _rentalPlanCreateLiveData

    private val _timeSlotLiveData = MutableLiveData<List<TimeSlot>?>()
    val timeSlotLiveData: LiveData<List<TimeSlot>?> = _timeSlotLiveData

    private val _selectedSlotsLiveData = MutableLiveData<SelectedTimeSlot?>()
    val selectedSlotsLiveData: LiveData<SelectedTimeSlot?> = _selectedSlotsLiveData

    fun getRentalPlanById(context: Context, id: Int) {
        viewModelScope.launch {
            val response = RentalPlanRepository.getRentalPlanById(context, id)
                _rentalPlanByIdLiveData.postValue(response)

        }
    }

    fun postRentalPlan(context: Context, rentalPlan: com.example.rentmycar.data.room.RentalPlan) {
        viewModelScope.launch {
            val response = RentalPlanRepository.postRentalPlan(context, rentalPlan)
            _postRentalPlanLiveData.postValue(response!!)
        }
    }

    fun createRentalPlan(rentalPlan: RentalPlan) {
        viewModelScope.launch {
            RentalPlanRepository.createRentalPlan(rentalPlan) {
                _rentalPlanCreateLiveData.postValue(it?.body())
            }
        }
    }

    fun createSelectedTimeslots(rentalId: Int, slots: List<SelectedTimeSlot>) {
        viewModelScope.launch {
            RentalPlanRepository.createTimeslots(slots, rentalId) {
                _selectedSlotsLiveData.postValue(it?.body())
            }
        }
    }

    fun getSelectedTimeslots() {
        viewModelScope.launch {
            RentalPlanRepository.getTimeslots {
                _timeSlotLiveData.postValue(it?.body())
            }
        }
    }
}