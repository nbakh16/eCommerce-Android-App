package com.nbakh.ecomuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nbakh.ecomuser.databinding.FragmentLoginBinding
import com.nbakh.ecomuser.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: FragmentLoginBinding
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
            // TODO: validate fields
            loginViewModel.loginUser(email, password)
        }

        binding.registerBtn.setOnClickListener {
            val email = binding.emailET.text.toString()
            val password = binding.passET.text.toString()
            // TODO: validate fields
            loginViewModel.registerUser(email, password)
        }

        return binding.root
    }

}