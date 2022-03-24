package com.example.rentmycar.ui.view.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.User
import com.example.rentmycar.ui.view.activity.LoginActivity
import com.example.rentmycar.ui.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment() {
    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createButton.setOnClickListener {
            if (validateSignInInputs()) {
                if (pwd1.text.toString() == pwd2.text.toString()) {
                    var user =
                        User(
                            0,
                            email.text.toString(),
                            lastname.text.toString(),
                            name.text.toString(),
                            pwd1.text.toString(),
                            1, "", "", "", "", "", "", "", "")


                    viewModel.addUser(requireContext(), user)
                } else {
                    Toast.makeText(requireContext(), "Not same password", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }


    fun validateSignInInputs(): Boolean {

        if (name?.text.isNullOrEmpty()) {
            name?.error = "Person Name cannot be empty."
            return false
        }
        if (lastname?.text.isNullOrEmpty()) {
            lastname?.error = "Person LastName cannot be empty."
            return false
        }
        if (email?.text.isNullOrEmpty()) {
            email?.error = "Person Email cannot be empty."
            return false
        }
        if (pwd1?.text.isNullOrEmpty()) {
            pwd1?.error = "Person Password cannot be empty."
            return false
        }
        if (pwd2?.text.isNullOrEmpty()) {
            pwd2?.error = "Person Password cannot be empty."
            return false
        }
        return true
    }
}