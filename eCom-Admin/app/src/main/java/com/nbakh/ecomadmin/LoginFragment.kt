package com.nbakh.ecomadmin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nbakh.ecomadmin.databinding.FragmentLoginBinding
import com.nbakh.ecomadmin.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel.authStateLD.observe(viewLifecycleOwner) {
            if (it == LoginViewModel.AuthState.AUTHENTICATED) {
                findNavController().popBackStack()
            }
        }

        loginViewModel.errMsgLD.observe(viewLifecycleOwner) {
            binding.errMsgTV.text = it
        }
        binding.loginBtn.setOnClickListener {
            val email = binding.emailET.text.toString()
            val password = binding.passET.text.toString()
            //validating
            if(email.isEmpty()){
                binding.errMsgTV.text = "Please enter your email!"
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.errMsgTV.text = "Please enter your password!"
                return@setOnClickListener
            }
            loginViewModel.loginAdmin(email, password)
        }
        return binding.root
    }

}