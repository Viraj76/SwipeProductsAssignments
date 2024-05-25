package com.example.swipeproducts.presentation.add_products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.swipeproducts.R
import com.example.swipeproducts.databinding.FragmentAddProductsBinding
import com.example.swipeproducts.utils.Constants


class AddProductsFragment : Fragment() {

    private lateinit var binding : FragmentAddProductsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductsBinding.inflate(layoutInflater)


        providingProductsTypes()
        onAddProductButtonClick()

        return binding.root
    }

    private fun providingProductsTypes() {
        val productsTypes = ArrayAdapter(requireContext() , R.layout.show_product_types, Constants.productList)
        binding.etProductType.setAdapter(productsTypes)
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