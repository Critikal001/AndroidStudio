package com.example.rentmycar.ui.epoxy

import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.LocalException

import com.example.rentmycar.databinding.ModelLocalExceptionBinding
import com.rentmycar.rentmycar.epoxy.ViewBindingKotlinModel


class EmptyListEpoxyModel(
    private val localException: LocalException
): ViewBindingKotlinModel<ModelLocalExceptionBinding>(R.layout.model_local_exception) {

    // Shows a custom title/description (error or not found for example) when API doesn't return results
    override fun ModelLocalExceptionBinding.bind() {
        titleTextView.text = localException.title
        descriptionTextView.text = localException.description
    }
}