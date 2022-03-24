package com.example.rentmycar.ui.view.fragment

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.rentmycar.AppPreference

import com.example.rentmycar.R
import com.example.rentmycar.databinding.FragmentLoginBinding
import com.example.rentmycar.sharedPrefFile
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.example.rentmycar.ui.view.activity.LoginActivity
import com.example.rentmycar.ui.viewmodel.UserViewModel

import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.password



class  LoginFragment : Fragment() {
    private val preference = AppPreference(LoginActivity.context)
    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        start()

        binding.loginButton.setOnClickListener {
            val userName: String = username.text.toString()
            val userPassword: String = password.text.toString()

            if (userName.isEmpty()) {
                username.error = "Email Required"
                username.requestFocus()
                return@setOnClickListener
            }
            if (userPassword.isEmpty()) {
                password.error = "Password Required"
                password.requestFocus()
                return@setOnClickListener
            }
            viewModel.userLogin(requireContext(), userName, userPassword)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun start() {
        val sharedPref = LoginActivity.context.getSharedPreferences(
            sharedPrefFile, Context.MODE_PRIVATE
        )

        val con = sharedPref.getBoolean("connected",false)
        if (con){
            val intent = Intent(requireContext(),HomeProviderActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

    }

}