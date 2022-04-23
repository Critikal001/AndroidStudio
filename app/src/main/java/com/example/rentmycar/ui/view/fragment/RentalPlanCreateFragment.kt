package com.example.rentmycar.ui.view.fragment

import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.util.Pair
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.RentalPlan
import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.example.rentmycar.ui.view.fragment.createRental.CreateCarFragmentArgs
import com.example.rentmycar.ui.view.fragment.createRental.CreateRentalFragmentDirections
import com.example.rentmycar.ui.viewmodel.RentalPlanViewModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_rental_plan_create.*
import java.text.SimpleDateFormat
import java.util.*


class RentalPlanCreateFragment : Fragment() {
    private val safeArgs: RentalPlanCreateFragmentArgs by navArgs()





    private val viewModel: RentalPlanViewModel by lazy {
        ViewModelProvider(this)[RentalPlanViewModel::class.java]
    }

    private val constraintsBuilder =
        CalendarConstraints.Builder()
            .setValidator(DateValidatorPointForward.now())

    private val dateRangePicker =
        MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText(HomeProviderActivity.context.getString(R.string.select_dates))
            .setSelection(
                Pair(
                    MaterialDatePicker.thisMonthInUtcMilliseconds(),
                    MaterialDatePicker.todayInUtcMilliseconds()
                )
            )
            .setCalendarConstraints(constraintsBuilder.build())
            .build()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rental_plan_create, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.postRentalPlanLiveData.observe(viewLifecycleOwner){ rentalplan ->

            if (rentalplan == null){
                Toast.makeText(requireActivity(), HomeCustomerActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }
            val directions = RentalPlanCreateFragmentDirections.actionRentalPlanCreateFragmentToCreateLocation(rentalId = safeArgs.rentalId, carId = safeArgs.carId, rentalPlanId = rentalplan)
            findNavController().navigate(directions)

        }

        // Fields cannot be typed in, instead add onClick listener that shows dateRangePicker and fills date
        start_date.isFocusable = false
        end_date.isFocusable = false
        start_date.setOnClickListener {
            dateRangePicker.show(childFragmentManager, "date_picker")
        }
        end_date.setOnClickListener {
            dateRangePicker.show(childFragmentManager, "date_picker")
        }

        dateRangePicker.addOnPositiveButtonClickListener { dateSelected ->
            start_date.text = SpannableStringBuilder(convertLongToTime(dateSelected.first))
            end_date.text = SpannableStringBuilder(convertLongToTime(dateSelected.second))
        }

        btnSubmit.setOnClickListener {
            // Set and post rentalPlan object.



            val rentalPlan = com.example.rentmycar.data.room.RentalPlan(id = 0,
                availableFrom = start_date.text.toString(),
                availableUntil = end_date.text.toString(),

            )
            viewModel.postRentalPlan(requireContext(), rentalPlan)


        }
    }



    private fun append(arr: Array<String>, element: String): Array<String> {
        val list: MutableList<String> = arr.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }

    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault())
        return format.format(date)
    }
}
