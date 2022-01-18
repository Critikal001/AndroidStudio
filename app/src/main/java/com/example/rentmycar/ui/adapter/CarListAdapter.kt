package com.example.rentmycar.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageLoader
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.Rental

class CarListAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader):
    RecyclerView.Adapter<CarListAdapter.RentalViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    private val rentalData = mutableListOf<Rental>()

    fun setData(rentalData: List<Rental>) {
        this.rentalData.clear()
        this.rentalData.addAll(rentalData)
        notifyDataSetChanged()}


    class RentalViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        val rentalName:TextView = containerView.findViewById(R.id.rental_title)
        val rentalType:TextView = containerView.findViewById(R.id.rental_info)
        val moviePoster: ImageView = containerView.findViewById(R.id.rental_image)

        fun bindData(rentalData: Rental){
            rentalName.text = rentalData.name
            rentalType.text = rentalData.car.model
//            imageLoader.loadImage(rentalData.poster, moviePoster)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RentalViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rental_list_fragment, viewGroup, false)

        return RentalViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: RentalViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bindData(rentalData[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = rentalData.size

}