package com.example.swipeproducts.presentation.products

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.swipeproducts.R
import com.example.swipeproducts.databinding.FragmentProductsBinding
import com.example.swipeproducts.domain.models.Product
import com.example.swipeproducts.presentation.products.adapters.ProductsAdapter
import com.example.swipeproducts.utils.NetworkManager
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProductsFragment : Fragment() {
    private val networkManager: NetworkManager by inject{ parametersOf(requireContext()) }

    private lateinit var binding: FragmentProductsBinding
    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var productsAdapter: ProductsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(layoutInflater)


        searchProducts()
        fetchingProducts()

        initializeAdapter()

        showingProducts()

        navigateToAddProductFragment()

        return binding.root
    }



    private fun searchProducts() {
        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val query = s.toString().trim()
                if(query.isEmpty()) binding.rvProducts.scrollToPosition(0) // when user clears the search field , then user should see the first product of the list.
                productsAdapter.filter.filter(query)
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    private fun showingNoDataLottie(show : Boolean){
        if(show){
            binding.animationView.visibility = View.VISIBLE
        }
        else{
            binding.animationView.visibility = View.GONE

        }
    }


    private fun navigateToAddProductFragment() {
        binding.fabAddProducts.setOnClickListener{
            findNavController().navigate(R.id.action_productsFragment_to_addProductsFragment)
        }
    }

    private fun showingProducts() {
        lifecycleScope.launch {
            viewModel.productList.collect { state ->
                when {
                    state.loading -> {
                        binding.shimmer.visibility = View.VISIBLE
                    }

                    state.productList.isNotEmpty() -> {

                        productsAdapter = ProductsAdapter(::showingNoDataLottie)
                        binding.rvProducts.adapter = productsAdapter

                        productsAdapter.differ.submitList(state.productList)
                        productsAdapter.originalList = state.productList as ArrayList<Product>
                        binding.shimmer.visibility = View.GONE
                    }

                    state.error.isNotEmpty() -> {
                        Log.d("error", state.error)
                    }

                }


            }
        }

    }


    private fun initializeAdapter() {

    }

    private fun fetchingProducts() {
        viewModel.getProductList()
    }


}