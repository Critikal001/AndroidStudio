package com.example.rentmycar.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rentmycar.data.model.api.post.EngineType

class RentalDetails: ViewModel() {
    //Car
    var constructionYear=0
    var mileage=0
    var model=""

    //Motor
    var motorNumber= ""
    var power= 0.0

    //EngineSpec
    var engineType= EngineType.BEVEngine
    var fuelType= ""
    var fuelUsePerKm= 0.0
    var fuelPrice= 0.0
    var pricePerKm= 0.0
    var costEngineType= 0.0
}