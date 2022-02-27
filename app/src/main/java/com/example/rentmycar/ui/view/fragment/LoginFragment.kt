package com.example.rentmycar.ui.view.fragment

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo

import android.widget.Toast
import com.example.rentmycar.R
import com.example.rentmycar.data.model.login.LoggedInUserView
import com.example.rentmycar.databinding.FragmentLoginBinding
import com.example.rentmycar.ui.view.activity.LoginActivity

import com.example.rentmycar.ui.viewmodel.LoginViewModel
import com.example.rentmycar.ui.viewmodel.factory.LoginViewModelFactory
import com.example.rentmycar.utils.sharedPrefFile
import com.example.rentmyuser.data.repositories.LoginRepository
import kotlinx.android.synthetic.main.fragment_login.*


class  LoginFragment : Fragment() {
    val repository: LoginRepository = LoginRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectButton.setOnClickListener {

            val userName = username.text.toString()
            val passWord = password.text.toString()

            if (userName.isEmpty()) {
                username.error = "Email RequuserNameired"
                username.requestFocus()

            }
            if (passWord.isEmpty()) {
                password.error = "Password Required"
                password.requestFocus()

            }


            repository.login(requireActivity(), userName, passWord)
            // val intent = Intent(this, HomeActivity::class.java)
            // startActivity(intent)

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.fragment_login, container, false)

}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences(
            sharedPrefFile, Context.MODE_PRIVATE
        )



        val con = sharedPref.getBoolean("connected",false)
        if (con){
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }


}