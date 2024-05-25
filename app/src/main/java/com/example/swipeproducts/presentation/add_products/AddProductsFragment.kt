package com.example.swipeproducts.presentation.add_products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swipeproducts.R
import com.example.swipeproducts.databinding.FragmentAddProductsBinding


class AddProductsFragment : Fragment() {

    private lateinit var binding : FragmentAddProductsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductsBinding.inflate(layoutInflater)


        onAddProductButtonClick()

        return binding.root
    }

    private fun onAddProductButtonClick() {
        binding.btnAddProduct.setOnClickListener{
            checkForEmptyFields()
        }
    }

    private fun checkForEmptyFields() {
        val productName = binding.etProductName.text
        val productType = binding.etProductType.text
        val productPrice = binding.etProductPrice.text
        val productTax = binding.etProductTax.text

        if (productName!!.isEmpty()) {
            binding.tilProductName.error = "Please provide Product Name"
        }
        if(productType!!.isEmpty()){
            binding.tilProductType.error = "Please provide Product Type"
        }
        if(productPrice!!.isEmpty()){
            binding.tilProductPrice.error = "Please provide Product Price"
        }
        if(productTax!!.isEmpty()){
            binding.tilProductTax.error = "Please provide Product Tax"
        }

//        } else {
//            textInputLayout.error = null // Clear the error
//        }
    }


}