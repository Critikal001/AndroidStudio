package com.example.rentmyengineSpec.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmycar.data.api.request.EngineSpecRequest
import com.example.rentmycar.data.model.api.post.EngineSpec
import com.example.rentmycar.data.room.EngineSpecRoom
import com.example.rentmyengineSpec.data.repositories.EngineSpecRepository


import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class EngineSpecViewModel: ViewModel() {


    private val _engineSpecListLiveData = MutableLiveData<List<EngineSpecRequest>?>()
    val engineSpecListLiveData: LiveData<List<EngineSpecRequest>?> = _engineSpecListLiveData

    private val _engineSpecResult = MutableLiveData<Int>()
    val engineSpecResult: LiveData<Int> get() = _engineSpecResult

    private val _engineSpecRoomLiveData = MutableLiveData<EngineSpecRoom?>()
    val engineSpecRoomLiveData: LiveData<EngineSpecRoom?> = _engineSpecRoomLiveData

    private val _engineSpecCreateResult = MutableLiveData<EngineSpecRequest?>()
    val engineSpecCreateResult: LiveData<EngineSpecRequest?> = _engineSpecCreateResult



    fun getEngineSpecById(id: Int) {
        viewModelScope.launch {
            val response = EngineSpecRepository.getEngineSpecList()
            _engineSpecListLiveData.postValue(response)
        }
    }



    fun saveEngineSpec(context: Context, engineSpecRoom: com.example.rentmycar.data.room.EngineSpecRoom) {
        viewModelScope.launch {
            val response = EngineSpecRepository.saveEngineSpec(context, engineSpecRoom)
            _engineSpecResult.value = response.toInt()
        }
    }

    fun getEngineSpec(context: Context, engineSpecId: Int) {
        viewModelScope.launch {
            val response = EngineSpecRepository.getEngineSpec(context, engineSpecId)
            _engineSpecRoomLiveData.postValue(response)
        }
    }

    fun createEngineSpec(engineSpec: EngineSpec) {
        viewModelScope.launch {
            EngineSpecRepository.createEngineSpec(engineSpec){
                _engineSpecCreateResult.postValue(it?.body())

            }
        }
    }


}