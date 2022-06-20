package com.nbakh.ecomadmin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.nbakh.ecomadmin.Adapter.DashboardItemAdapter
import com.nbakh.ecomadmin.databinding.FragmentDashboardBinding
import com.nbakh.ecomadmin.model.dashboardItemList

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val adapter = DashboardItemAdapter()
        val gridLayoutManager = GridLayoutManager(requireActivity(), 2)
        binding.dashboardRV.adapter = adapter
        binding.dashboardRV.layoutManager = gridLayoutManager
        return binding.root
    }

}