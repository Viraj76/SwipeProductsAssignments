package com.example.swipeproducts.presentation.add_products

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.swipeproducts.R
import com.example.swipeproducts.databinding.FragmentAddProductsBinding
import com.example.swipeproducts.utils.Constants
import com.example.swipeproducts.utils.isValidImage
import com.example.swipeproducts.utils.showToast


class AddProductsFragment : Fragment() {

    private lateinit var binding : FragmentAddProductsBinding
    private var selectedImageUri: Uri? = null
    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                selectedImageUri = it
                if (it.isValidImage(requireContext())) {
                    binding.ivProduct.setImageURI(uri)
                }


            }
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductsBinding.inflate(layoutInflater)


        providingProductsTypes()
        selectingAnImage()
        onAddProductButtonClick()


        return binding.root
    }

    private fun selectingAnImage() {
        binding.cvAddImage.setOnClickListener{
            getContent.launch("image/*")
        }
    }

    private fun providingProductsTypes() {
        val productsTypes = ArrayAdapter(requireContext() , R.layout.show_product_types, Constants.productList)
        binding.etProductType.setAdapter(productsTypes)
    }

    private fun onAddProductButtonClick() {
        binding.btnAddProduct.setOnClickListener{
            val productName = binding.etProductName.text.toString()
            val productType = binding.etProductType.text.toString()
            val productPrice = binding.etProductPrice.text.toString()
            val productTax = binding.etProductTax.text.toString()

            if(!checkForEmptyFields(productName,productType,productPrice,productTax)){
                //
            }


        }
    }

    private fun checkForEmptyFields(
        productName: String,
        productType: String,
        productPrice: String,
        productTax: String
    ) : Boolean{

        if (productName.isEmpty()) {
            binding.tilProductName.error = "Please provide Product Name"
            return false
        }
        if(productType.isEmpty()){
            binding.tilProductType.error = "Please provide Product Type"
            return false
        }
        if(productPrice.isEmpty()){
            binding.tilProductPrice.error = "Please provide Product Price"
            return false
        }
        if(productTax.isEmpty()){
            binding.tilProductTax.error = "Please provide Product Tax"
            return false
        }

        if(selectedImageUri == null){
            showToast("Please select an image")
            return false
        }

        return true
//        } else {
//            textInputLayout.error = null // Clear the error
//        }


    }
}