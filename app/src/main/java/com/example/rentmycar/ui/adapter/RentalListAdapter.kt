package com.example.rentmycar.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.Car
import com.example.rentmycar.data.model.api.post.Engine
import com.example.rentmycar.data.model.api.post.EngineSpec
import com.example.rentmycar.data.model.api.post.Rental
import com.example.rentmycar.data.model.api.request.GetRental
import com.example.rentmycar.ui.viewmodel.RentalDetails
import com.example.rentmycar.ui.viewmodel.ReservationDetails

class RentalListAdapter(
    val context: Context,
    var rental: List<GetRental>,
    var car: List<Car>,
    var engine: List<Engine>,
    var engineSpec: List<EngineSpec>,
    var rentalModel: RentalDetails,
    var reservationModel: ReservationDetails,
//    var resViewModel: Reservation
): RecyclerView.Adapter<MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(-R.layout.rental_layout, parent, false))

    }

    override fun getItemCount() = rental.size

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nomVehicule.text = rental[position].name
// Images with glide
//        Glide.with(context).load(this!!.data!![position].secureUrl).into(holder.imageVehicule)

        holder.detailsButton.setOnClickListener{
            rentalModel.constructionYear = car[position].constructionYear
            rentalModel.mileage =  car[position].mileage
            rentalModel.model = car[position].model
            rentalModel.motorNumber = engine[position].motorNumber
            rentalModel.power = engine[position].power
            rentalModel.engineType = engineSpec[position].engineType
            rentalModel.fuelType = engineSpec[position].fuelType
            rentalModel.fuelUsePerKm =engineSpec[position].fuelUsePerKm
            rentalModel.fuelPrice =engineSpec[position].fuelPrice
            rentalModel.pricePerKm =engineSpec[position].pricePerKm
//            vm.etat = data[position].etat
//            vm.limiteurVitesse =  data[position].limiteurVitesse
//            vm.secureUrl = data[position].secureUrl
//            vm.longitude = data[position].longitude
//            vm.latitute = data[position].latitude
//            vmRes.idBorneDepart = resViewModel.idBorneDepart
//            vmRes.idBorneDestination = resViewModel.idBorneDestination
//            vmRes.tempsEstimeEnSecondes = resViewModel.tempsEstimeEnSecondes
//            vmRes.tempsEstimeHumanReadable = resViewModel.tempsEstimeHumanReadable
//            Log.i("VehiculeAdapter","distanceEstime"+resViewModel.distanceEstime.toString() )
//            Log.i("VehiculeAdapter","distanceEstime"+resViewModel.tempsEstimeEnSecondes.toString())
//            vmRes.distanceEstime = resViewModel.distanceEstime
            holder.detailsButton.findNavController().navigate(R.id.action_listeVehiculeFragment_to_detailsVehiculeFragment)
        }
        holder.reserverButton.setOnClickListener{
            reservationModel.rentalId = rental[position].rentalId
//            vm.modele =  data[position].modele
//            vm.secureUrl = data[position].secureUrl
//            vm.numChassis = data[position].numChassis
//            vm.longitude = data[position].longitude
//            vm.latitute = data[position].latitude
//            vmRes.idBorneDepart = resViewModel.idBorneDepart
//            vmRes.idBorneDestination = resViewModel.idBorneDestination
//            vmRes.tempsEstimeEnSecondes = resViewModel.tempsEstimeEnSecondes
//            vmRes.tempsEstimeHumanReadable = resViewModel.tempsEstimeHumanReadable
//            Log.i("VehiculeAdapter","idBornDepart"+resViewModel.idBorneDepart.toString() )
//            Log.i("VehiculeAdapter","distanceEstime"+resViewModel.distanceEstime.toString() )
//            Log.i("VehiculeAdapter","tempsEstimeEnSecondes"+resViewModel.tempsEstimeEnSecondes.toString())
//            vmRes.distanceEstime = resViewModel.distanceEstime
            holder.reserverButton.findNavController().navigate(R.id.action_listeVehiculeFragment_to_infosReservationFragment)
        }



    }

}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nomVehicule = view.findViewById<TextView>(R.id.nameRental) as TextView
    val imageVehicule = view.findViewById<ImageView>(R.id.imageRental) as ImageView
    val detailsButton = view.findViewById(R.id.detailsButton) as Button
    val reserverButton = view.findViewById(R.id.reserveButton) as Button
}