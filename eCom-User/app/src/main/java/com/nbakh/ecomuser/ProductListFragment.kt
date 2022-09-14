package com.nbakh.ecomuser

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.GridLayoutManager
import com.nbakh.ecomuser.Adapter.ProductAdapter
import com.nbakh.ecomuser.databinding.FragmentProductListBinding
import com.nbakh.ecomuser.model.CartItem
import com.nbakh.ecomuser.model.Product
import com.nbakh.ecomuser.utils.CartAction
import com.nbakh.ecomuser.viewmodel.LoginViewModel
import com.nbakh.ecomuser.viewmodel.ProductViewModel
import com.nbakh.ecomuser.viewmodel.UserViewModel

class ProductListFragment : Fragment(), MenuProvider {
    private val loginViewModel: LoginViewModel by activityViewModels()
    private val productViewModel: ProductViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.addMenuProvider(this)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.product_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return menuItem.onNavDestinationSelected(findNavController())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        val adapter = ProductAdapter {action, cartItem ->
            performCartAction(action, cartItem)
        }
        val glm = GridLayoutManager(requireActivity(), 2)
        binding.productRV.layoutManager = glm
        binding.productRV.adapter = adapter
        loginViewModel.authStateLD.observe(viewLifecycleOwner) {
            if (it == LoginViewModel.AuthState.UNAUTHENTICATED) {
                findNavController()
                    .navigate(R.id.action_productListFragment_to_loginFragment)
            }
        }

        userViewModel.getCartItems(loginViewModel.firebaseAuth.currentUser?.uid!!)
            .observe(viewLifecycleOwner) { cartList ->
                productViewModel.getProducts().observe(viewLifecycleOwner) {productList ->
                    if (productList.isEmpty()) {
                        binding.mProgressBar.visibility = View.VISIBLE
                    } else {
                        binding.mProgressBar.visibility = View.GONE
                    }
                    val tempList = mutableListOf<Product>()
                    for (product in productList) {
                        for (cart in cartList) {
                            if (product.id == cart.productId) {
                                product.inCart = true
                            }
                        }
                        tempList.add(product)
                    }
                    adapter.submitList(tempList)
                }
            }



        return binding.root
    }

    private fun performCartAction(action: String, cartItem: CartItem) {
        when(action) {
            CartAction.addToCart -> {
                userViewModel.addToCart(loginViewModel.firebaseAuth.currentUser?.uid!!, cartItem)
            }
            CartAction.removeFromCart -> {
                userViewModel.removeFromCart(loginViewModel.firebaseAuth.currentUser?.uid!!, cartItem.productId!!)
            }
        }
    }

}