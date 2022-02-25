package com.rentmycar.rentmycar.epoxy

import com.example.rentmycar.R
import com.example.rentmycar.databinding.HeaderBinding


data class HeaderEpoxyModel(
    val header: String
): ViewBindingKotlinModel<HeaderBinding>(R.layout.header) {
    override fun HeaderBinding.bind() {
        titleTextView.text = header
    }

}