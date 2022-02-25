package com.example.rentmycar.ui.view.fragment

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.rentmycar.R
import com.example.rentmycar.data.model.api.post.User
import com.example.rentmycar.ui.view.activity.LoginActivity
import com.example.rentmycar.ui.viewmodel.LoginViewModel
import com.example.rentmyuser.data.repositories.LoginRepository
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment() {
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }
    private val reposity: LoginRepository = LoginRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginResult.observe(viewLifecycleOwner) { register ->
            btnRegister.isEnabled = true
            btnRegister.text = LoginActivity.context.getString(R.string.create_an_account)


            if (register == null) {
                Toast.makeText(requireActivity(), LoginActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
                return@observe
            }
        }

        btnRegister.setOnClickListener {
            val userName: String = email.editText?.text?.trim { it <= ' ' }.toString()
            val userPassword: String = password.editText?.text?.trim { it <= ' ' }.toString()
            val userFirstName: String = first_name.editText?.text?.trim { it <= ' ' }.toString()
            val userLastName: String = last_name.editText?.text?.trim { it <= ' ' }.toString()

            if (!validateRegisterForm(userName, userPassword, userFirstName, userLastName)) {
                val register = User(
                    userName = userName,
                    passWord = userPassword,
                    firstName = userFirstName,
                    lastName = userLastName,
                    userRole = 1,
                    city = null,
                    country = null,
                    email = null,
                    houseNumber = null,
                    iban = null,
                    phoneNumber = null,
                    postalCode = null,
                    street = null
                )
                btnRegister.isEnabled = false
                btnRegister.text = LoginActivity.context.getString(R.string.registering)


                viewModel.registerUser(register)
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun validateRegisterForm(
        userEmail: String,
        userPassword: String,
        userFirstName: String,
        userLastName: String
    ): Boolean {
        var formErrors = false

        if (userEmail.isEmpty()) {
            email.error = LoginActivity.context.getString(R.string.email_empty)
            formErrors = true
        } else {
            email.error = null
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
           email.error = LoginActivity.context.getString(R.string.email_incorrect)
            formErrors = true
        } else {
            email.error = null
        }

        if (userPassword.isEmpty()) {
            password.error = LoginActivity.context.getString(R.string.password_empty)
            formErrors = true
        } else {
            password.error = null
        }

        if (userPassword.length < 6) {
            password.error = LoginActivity.context.getString(R.string.password_incorrect)
            formErrors = true
        } else {
            password.error = null
        }

        if (userFirstName.length < 2) {
            first_name.error = LoginActivity.context.getString(R.string.name_incorrect)
            formErrors = true
        } else {
            password.error = null
        }

        if (userLastName.length < 2) {
            last_name.error = LoginActivity.context.getString(R.string.name_incorrect)
            formErrors = true
        } else {
            password.error = null
        }

        return formErrors
    }
}