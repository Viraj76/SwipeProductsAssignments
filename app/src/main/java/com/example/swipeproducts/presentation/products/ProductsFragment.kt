package com.example.swipeproducts.presentation.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pools
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.swipeproducts.R
import com.example.swipeproducts.databinding.FragmentProductsBinding
import com.example.swipeproducts.presentation.products.adapters.ProductsAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProductsFragment : Fragment() {
    private lateinit var binding: FragmentProductsBinding
    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var productsAdapter: ProductsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(layoutInflater)



        fetchingProducts()

        initializeAdapter()

        showingProducts()


        return binding.root
    }

    private fun showingProducts() {


        lifecycleScope.launch {
            viewModel.productList.collect { state ->
                when {
                    state.loading -> {
                        binding.shimmer.visibility = View.VISIBLE
                    }

                    state.productList.isNotEmpty() -> {
                        productsAdapter.differ.submitList(state.productList)
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
        productsAdapter = ProductsAdapter()
        binding.rvProducts.adapter = productsAdapter
    }

    private fun fetchingProducts() {
        viewModel.getProductList()
    }


}