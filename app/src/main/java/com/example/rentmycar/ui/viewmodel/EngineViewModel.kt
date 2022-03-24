package com.example.rentmycar.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmycar.data.api.request.EngineRequest
import com.example.rentmycar.data.model.api.post.Engine
import com.example.rentmycar.data.repositories.EngineRepository
import com.example.rentmycar.data.room.EngineRoom
import kotlinx.coroutines.launch


class EngineViewModel : ViewModel() {

    private val _engineListLiveData = MutableLiveData<List<EngineRequest>?>()
    val engineListLiveData: LiveData<List<EngineRequest>?> = _engineListLiveData

    private val _engineResult = MutableLiveData<Int>()
    val engineResult: LiveData<Int> get() = _engineResult

    private val _engineRoomLiveData = MutableLiveData<EngineRoom?>()
    val engineRoomLiveData: LiveData<EngineRoom?> = _engineRoomLiveData

    private val _engineCreateResult = MutableLiveData<EngineRequest?>()
    val engineResourceResult: LiveData<EngineRequest?> = _engineCreateResult



    fun getEngineList(id: Int) {
        viewModelScope.launch {
            val response = EngineRepository.getEngineList()
            _engineListLiveData.postValue(response)
        }
    }



    fun saveEngine(context: Context, engineRoom: EngineRoom) {
        viewModelScope.launch {
            val response = EngineRepository.saveEngine(context, engineRoom)
            _engineResult.value = response.toInt()
        }
    }

    fun getEngine(context: Context, engineId: Int) {
        viewModelScope.launch {
            val response = EngineRepository.getEngine(context, engineId)
            _engineRoomLiveData.postValue(response)
        }
    }

    fun createEngine(engine: Engine) {
        viewModelScope.launch {
            EngineRepository.createEngine(engine){
                _engineCreateResult.postValue(it?.body())

            }
        }
    }
}