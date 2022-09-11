package com.nbakh.ecomadmin

import com.nbakh.ecomadmin.customdialogs.DatePickerFragment
import com.nbakh.ecomadmin.databinding.FragmentProductDetailsBinding
import com.nbakh.ecomadmin.model.Purchase
import com.nbakh.ecomadmin.utils.getFormattedDate
import com.nbakh.ecomadmin.viewmodel.ProductViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import com.google.firebase.Timestamp

class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private val productViewModel: ProductViewModel by activityViewModels()
    private var id: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        id = arguments?.getString("id")
        id?.let {
            productViewModel.getProductById(it).observe(viewLifecycleOwner) {
                binding.product = it
            }
        }
        id?.let {
            productViewModel.getPurchaseListByProductId(it).observe(viewLifecycleOwner) {
                var purchaseHistory = ""
                it.forEach {
                    purchaseHistory += "${getFormattedDate(it.purchaseDate!!.seconds * 1000, "dd/MM/yyyy")} - Qty ${it.quantity} - Price: ${it.purchasePrice}\n"
                }
                binding.purchaseHistoryTV.text = purchaseHistory
            }
        }

        binding.repurchaseBtn.setOnClickListener {
            //showRepurchaseDialog()
        }

        return binding.root
    }

//    private fun showRepurchaseDialog() {
//        var purchaseTime: Timestamp? = null
//        val builder = AlertDialog.Builder(requireActivity()).apply {
//            setTitle("Re-Purchase Product")
//            val rlbinding = RepurchaseLayoutBinding.inflate(LayoutInflater.from(requireContext()))
//            setView(rlbinding.root)
//            rlbinding.repurchaseDateBtn.setOnClickListener {
//                DatePickerFragment{
//                    purchaseTime = it
//                    rlbinding.repurchaseDateBtn.text = getFormattedDate(it.seconds * 1000, "dd/MM/yyyy")
//                }.show(childFragmentManager, null)
//            }
//            setPositiveButton("Buy") { dilaog, value ->
//                val qty = rlbinding.repurchaseQtyET.text.toString()
//                val price = rlbinding.repurchasePriceET.text.toString()
//                // TODO: validate
//                val purchase = Purchase(
//                    productId = id,
//                    purchaseDate = purchaseTime,
//                    purchasePrice = price.toDouble(),
//                    quantity = qty.toDouble()
//                )
//                productViewModel.addRepurchase(purchase)
//            }
//            setNegativeButton("Close", null)
//        }
//        val dialog: AlertDialog = builder.create()
//        dialog.show()
//    }

}