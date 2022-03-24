package com.example.rentmycar.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class PaymentViewModel: ViewModel() {

//    private val paymentRepository = PaymentRepository()
//
//    private val _paymentLiveData = MutableLiveData<GetPaymentResponse?>()
//    val paymentLiveData: LiveData<GetPaymentResponse?> = _paymentLiveData
//
//    private val _paymentCallbackLiveData = MutableLiveData<String?>()
//    val paymentCallbackLiveData: LiveData<String?> = _paymentCallbackLiveData
//
//    fun postPayment(payment: PostPaymentRequest) {
//        viewModelScope.launch {
//            val response = paymentRepository.postPayment(payment)
//            _paymentLiveData.postValue(response)
//        }
//    }
//
//    fun getPayment(id: Int) {
//        viewModelScope.launch {
//            val response = paymentRepository.getPayment(id)
//            _paymentLiveData.postValue(response)
//        }
//    }
//
//    fun postPaymentCallback(id: Int, callback: PostPaymentCallbackRequest) {
//        viewModelScope.launch {
//            val response = paymentRepository.postPayment(id, callback)
//            _paymentCallbackLiveData.postValue(response)
//        }
//    }
}