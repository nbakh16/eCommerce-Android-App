package com.nbakh.ecomadmin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.nbakh.ecomadmin.Adapter.DashboardAdapter
import com.nbakh.ecomadmin.databinding.FragmentDashboardBinding
import com.nbakh.ecomadmin.model.DashboardItemType
import com.nbakh.ecomadmin.viewmodel.LoginViewModel

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private val loginViewModel: LoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        loginViewModel.authStateLD.observe(viewLifecycleOwner) {
            if (it == LoginViewModel.AuthState.UNAUTHENTICATED) {
                findNavController().navigate(R.id.action_dashboardFragment_to_loginFragment)
            }
        }
        val adapter = DashboardAdapter{
            navigateToDashboardItemPage(it)
        }
        val glm = GridLayoutManager(requireActivity(), 2)
        binding.dashboardRV.layoutManager = glm
        binding.dashboardRV.adapter = adapter

        return binding.root
    }

    private fun navigateToDashboardItemPage(it: DashboardItemType) {
        when(it) {
            DashboardItemType.ADD_PRODUCT -> findNavController().navigate(R.id.action_dashboardFragment_to_addProductFragment)
            DashboardItemType.VIEW_PRODUCT -> findNavController().navigate(R.id.action_dashboardFragment_to_viewProductFragment)
            DashboardItemType.CATEGORY -> findNavController().navigate(R.id.action_dashboardFragment_to_categoryFragment)
            DashboardItemType.LOGOUT -> loginViewModel.logout()
        }
    }
}