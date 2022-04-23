package com.example.rentmycar.ui.view.fragment

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rentmycar.R
import com.example.rentmycar.ui.controllers.RentalDetailFragmentController
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.example.rentmycar.ui.viewmodel.RentalViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.rental_detail_fragment.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class RentalDetailFragment : Fragment() {
    private val viewModel: RentalViewModel by lazy {
        ViewModelProvider(this)[RentalViewModel::class.java]
    }
    private val controller = RentalDetailFragmentController(::onLocationBtnClicked,
        ::onEditLocationBtnClicked, ::onEditCarBtnClicked, ::onBookNowBtnClicked)
    private val safeArgs: RentalDetailFragmentArgs by navArgs()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rental_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            viewModel.rentalDetailResult.observe(viewLifecycleOwner) { rental ->
                controller.rental = rental
                if (rental == null) {
                    Toast.makeText(requireActivity(),
                        HomeCustomerActivity.context.getString(R.string.network_call_failed),
                        Toast.LENGTH_LONG).show()
                    return@observe
                }

//            // Only show edit buttons if car belongs to logged in user
//            if (userId == car.userId) {
                btnAddResource.visibility = View.VISIBLE
            }



        val rentalId = safeArgs.rentalId
        viewModel.getRentalById(rentalId)

        // Upload image to car and set request code 101
        btnAddResource.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .galleryMimeTypes(
                    mimeTypes = arrayOf(
                        "images/png",
                        "images/jpg"))

                .compress(1024)
                .start(101)
        }

        observeRentalResource()

        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(controller)


    }

    private fun onLocationBtnClicked(id: Int) {
        val directions =
            RentalDetailFragmentDirections.actionCarDetailsFragmentToLocationDetailsFragment()
        findNavController().navigate(directions)
    }

    private fun onEditLocationBtnClicked(id: Int) {
        val directions =
            RentalDetailFragmentDirections.actionCarDetailsFragmentToLocationCreateFragment()
        findNavController().navigate(directions)
    }

    private fun onEditCarBtnClicked(id: Int) {
        val directions =
            RentalDetailFragmentDirections.actionCarDetailsFragmentToCarCreateFragment()
        findNavController().navigate(directions)
    }

    private fun onBookNowBtnClicked(rentalId: Int) {
        val directions =
            RentalDetailFragmentDirections.actionCarDetailsFragmentToCarAvailabilityFragment(rentalId)
        findNavController().navigate(directions)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Listen for request 101 on activity, post image to api if successful
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            val file = File(uri?.path)
            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            val image = MultipartBody.Part.createFormData("file","file", requestFile)

            Toast.makeText(requireActivity(), HomeCustomerActivity.context.getString(R.string.uploading_image), Toast.LENGTH_LONG).show()

            viewModel.uploadImage(safeArgs.rentalId,image)
        }
    }

    private fun observeRentalResource() {
        viewModel.rentalImageResult.observe(viewLifecycleOwner) { response ->
            if (response == null) {
                Toast.makeText(requireActivity(), HomeCustomerActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }

            Toast.makeText(requireActivity(), HomeCustomerActivity.context.getString(R.string.image_uploaded), Toast.LENGTH_LONG).show()

            val directions = RentalDetailFragmentDirections.actionCarDetailsFragmentToCarDetailsFragment(rentalId = safeArgs.rentalId)
            findNavController().navigate(directions)
        }
    }
}