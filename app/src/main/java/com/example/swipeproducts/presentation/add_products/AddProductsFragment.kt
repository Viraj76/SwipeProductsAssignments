package com.example.swipeproducts.presentation.add_products

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.swipeproducts.R
import com.example.swipeproducts.data.di.dataModules
import com.example.swipeproducts.data.dto.notification.Notification
import com.example.swipeproducts.data.dto.notification.NotificationData
import com.example.swipeproducts.data.remote.api.ApiUtilities
import com.example.swipeproducts.databinding.FragmentAddProductsBinding
import com.example.swipeproducts.databinding.PostingDoneBinding
import com.example.swipeproducts.utils.Constants
import com.example.swipeproducts.utils.hideDialog
import com.example.swipeproducts.utils.hidePostDoneDialog
import com.example.swipeproducts.utils.isValidImage
import com.example.swipeproducts.utils.showDialog
import com.example.swipeproducts.utils.showPostDoneDialog
import com.example.swipeproducts.utils.showToast
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.FilterOutputStream


class AddProductsFragment : Fragment() {

    private lateinit var binding: FragmentAddProductsBinding
    private val viewModel: AddProductsViewModel by viewModels()
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
        observingPostingProductStatus()
        return binding.root
    }

    private fun observingPostingProductStatus() {
        lifecycleScope.launch {
            viewModel.postProduct.collect { state ->
                when {
                    state.loading -> {
                        showDialog("Posting Your Products....")
                    }

                    state.error.isNotEmpty() -> {
                        showDialog(state.error)
                    }

                    state.data != null -> {
                        /*
                        Here the posting is too fast such that the dialog is almost not visible , i.e.
                        I have added delay of 2 seconds to see the dialog only
                         */
                        FirebaseMessaging.getInstance().token.addOnCompleteListener{
                            val token = it.result
                            val title = state.data.message
                            val body = "Product Name - ${state.data.product_details.product_name} , Product Id - ${state.data.product_id}"
                            val notification = Notification(token , NotificationData(title , body))
                            Log.d("sendnoti" , notification.toString())
                            viewModel.sendNotification(notification = notification)
                        }


                        delay(2000)
                        hideDialog()
                        clearAllField()         // clear all the fields after posting one product
                        viewModel.update()      // clearing the state , to avoid bugs
                        showPostDoneDialog()   // showing done dialog for better UX
                        delay(4500)
                        hidePostDoneDialog()   // hiding the dialog after 4.5 seconds

                    }
                }
            }
        }
    }




    private fun selectingAnImage() {
        binding.cvAddImage.setOnClickListener {
            getContent.launch("image/*")
        }
    }

    private fun providingProductsTypes() {
        val productsTypes =
            ArrayAdapter(requireContext(), R.layout.show_product_types, Constants.productList)
        binding.etProductType.setAdapter(productsTypes)
    }

    @SuppressLint("Recycle")
    private fun onAddProductButtonClick() {
        binding.btnAddProduct.setOnClickListener {
            val productName = binding.etProductName.text.toString()
            val productType = binding.etProductType.text.toString()
            val productPrice = binding.etProductPrice.text.toString()
            val productTax = binding.etProductTax.text.toString()

            if (checkForEmptyFields(productName, productType, productPrice, productTax)) {

                val filesDir = activity?.applicationContext!!.filesDir
                val file = File(filesDir, "image.png")

                val inputStream = activity?.contentResolver?.openInputStream(selectedImageUri!!)
                val outputStream = FileOutputStream(file)

                inputStream!!.copyTo(outputStream)

                val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())

                val part = MultipartBody.Part.createFormData("profile", file.name, requestBody)


                lifecycleScope.launch {
                    Log.d("addprodviewm", "posting products")
                    viewModel.postProducts(productName, productType, productPrice, productTax, part)
                    Log.d("addprodviewm", viewModel.postProduct.value.toString())
                }
            }


        }
    }


    private fun clearAllField() {
        binding.etProductName.text?.clear()
        binding.etProductType.text?.clear()
        binding.etProductPrice.text?.clear()
        binding.etProductTax.text?.clear()

        binding.ivProduct.setImageURI(null)
        binding.tilProductName.clearFocus()
        binding.tilProductType.clearFocus()
        binding.tilProductPrice.clearFocus()
        binding.tilProductTax.clearFocus()

        binding.ivProduct.setImageResource(R.drawable.baseline_add_photo_alternate_24)

    }

    private fun checkForEmptyFields(
        productName: String,
        productType: String,
        productPrice: String,
        productTax: String
    ): Boolean {

        // checking if there is any field empty or not
        if (productName.isEmpty()) {
            binding.tilProductName.error = "Please provide Product Name"
            return false
        } else if (productType.isEmpty()) {
            binding.tilProductType.error = "Please provide Product Type"
            return false
        } else if (productPrice.isEmpty()) {
            binding.tilProductPrice.error = "Please provide Product Price"
            return false
        } else if (productTax.isEmpty()) {
            binding.tilProductTax.error = "Please provide Product Tax"
            return false
        } else if (selectedImageUri == null) {
            showToast("Please select an image")
            return false
        } else return true
//        } else {
//            textInputLayout.error = null // Clear the error
//        }


    }
}