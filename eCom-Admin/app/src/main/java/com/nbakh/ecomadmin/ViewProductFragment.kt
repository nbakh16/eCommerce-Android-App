package com.nbakh.ecomadmin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbakh.ecomadmin.Adapter.ProductAdapter
import com.nbakh.ecomadmin.databinding.FragmentViewProductBinding
import com.nbakh.ecomadmin.viewmodel.ProductViewModel

class ViewProductFragment : Fragment() {
    private lateinit var binding: FragmentViewProductBinding
    private val productViewModel: ProductViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewProductBinding.inflate(inflater, container, false)
        val adapter = ProductAdapter(){
            findNavController()
                .navigate(R.id.action_viewProductFragment_to_productDetailsFragment, args = bundleOf("id" to it))
        }
        binding.productRV.layoutManager = LinearLayoutManager(requireActivity())
        binding.productRV.adapter = adapter
        productViewModel.getProducts().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        return binding.root
    }

}