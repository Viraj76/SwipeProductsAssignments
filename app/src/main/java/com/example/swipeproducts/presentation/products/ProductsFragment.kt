package com.example.swipeproducts.presentation.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.swipeproducts.R
import com.example.swipeproducts.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {
    private lateinit var binding : FragmentProductsBinding
    private val viewModel : ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentProductsBinding.inflate(layoutInflater)


        viewModel.getProductList()

        return binding.root
    }


}