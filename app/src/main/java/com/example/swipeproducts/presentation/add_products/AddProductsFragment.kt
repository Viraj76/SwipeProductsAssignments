package com.example.swipeproducts.presentation.add_products

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
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
import androidx.navigation.fragment.findNavController
import com.example.swipeproducts.R
import com.example.swipeproducts.data.dto.notification.Notification
import com.example.swipeproducts.data.dto.notification.NotificationData
import com.example.swipeproducts.databinding.FragmentAddProductsBinding
import com.example.swipeproducts.utils.Constants
import com.example.swipeproducts.utils.NetworkManager
import com.example.swipeproducts.utils.hideDialog
import com.example.swipeproducts.utils.hidePostDoneDialog
import com.example.swipeproducts.utils.isValidImage
import com.example.swipeproducts.utils.showDialog
import com.example.swipeproducts.utils.showPostDoneDialog
import com.example.swipeproducts.utils.showToast
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.io.File
import java.io.FileOutputStream


class AddProductsFragment : Fragment() {
    private val networkManager: NetworkManager by inject { parametersOf(requireContext()) }
    private val firebaseMessaging: FirebaseMessaging by inject()
    private lateinit var binding: FragmentAddProductsBinding
    private val viewModel: AddProductsViewModel by viewModels()
    private var selectedImageUri: Uri? = null

    // getting Image uri selected by the user
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
        Log.d(TAG, "onCreateView : Add Products Fragments")
        binding = FragmentAddProductsBinding.inflate(layoutInflater)

        backToProductFragment()

        observeNetwork()    // observing network , without network we can't post products

        providingProductsTypes()    // passing list to the product type , showing list in ArrayAdapter

        selectingAnImage()     // selecting an image

        onAddProductButtonClick()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observingPostingProductStatus()   // monitoring posting products status
        Log.d(TAG, "onViewCreated : Add Products Fragments")
    }

    private fun backToProductFragment() {
        binding.ivBackToProductFragment.setOnClickListener {
            findNavController().navigate(R.id.action_addProductsFragment_to_productsFragment)

        }
    }

    private fun observeNetwork() {
        networkManager.observe(viewLifecycleOwner) { hasInternet ->
            if (!hasInternet) {
                // show no internet indication
                binding.llNoInternet.visibility = View.VISIBLE
                binding.animationView.visibility = View.INVISIBLE
                binding.llAddingProducts.visibility = View.INVISIBLE
                binding.btnAddProduct.visibility = View.INVISIBLE
            } else {

                binding.llNoInternet.visibility = View.INVISIBLE
                binding.animationView.visibility = View.VISIBLE
                binding.llAddingProducts.visibility = View.VISIBLE
                binding.btnAddProduct.visibility = View.VISIBLE
            }
        }
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
                        // sending notification first
                        firebaseMessaging.token.addOnCompleteListener {
                            val token =
                                it.result    // important for targeting device to send notification , here personal phone token would be accessed
                            val title = state.data.message
                            val body =
                                "Product Name - ${state.data.product_details.product_name} , Product Id - ${state.data.product_id}"
                            val notification = Notification(token, NotificationData(title, body))
                            viewModel.sendNotification(notification = notification)
                        }
                        /*
                         Here the posting is too fast such that the dialog is almost not visible , i.e.
                         I have added delay of 2 seconds to see the dialog only
                         */
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
                val image = getImageMultipart() // select image , optionally

                lifecycleScope.launch {
                    viewModel.postProducts(productName, productType, productPrice, productTax, image)
                }
            }
        }
    }

    private fun getImageMultipart(): MultipartBody.Part? {
        val filesDir = activity?.applicationContext!!.filesDir
        val file = File(filesDir, "image.png")

        // Check if the selected image URI is not null
        if (selectedImageUri != null) {
            val inputStream = activity?.contentResolver?.openInputStream(selectedImageUri!!)
            val outputStream = FileOutputStream(file)

            inputStream!!.copyTo(outputStream)

            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            return MultipartBody.Part.createFormData("profile", file.name, requestBody)
        } else {
            // If selectedImageUri is null, return a default or empty MultipartBody.Part
            val dummyFile = File(filesDir, "dummy_image.png")
            dummyFile.createNewFile() // Create an empty dummy file

            val requestBody = dummyFile.asRequestBody("image/*".toMediaTypeOrNull())
            return MultipartBody.Part.createFormData("profile", dummyFile.name, requestBody)
        }
    }



    private fun clearAllField() {
        // clear text
        binding.etProductName.text?.clear()
        binding.etProductType.text?.clear()
        binding.etProductPrice.text?.clear()
        binding.etProductTax.text?.clear()

        //clear focus
        binding.ivProduct.setImageURI(null)
        binding.tilProductName.clearFocus()
        binding.tilProductType.clearFocus()
        binding.tilProductPrice.clearFocus()
        binding.tilProductTax.clearFocus()

        // clear errors
        binding.tilProductName.error = null
        binding.tilProductType.error = null
        binding.tilProductPrice.error = null
        binding.tilProductTax.error = null

        // show default image
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
        } else return true
    }


    override fun onStart() {
        super.onStart()
        Log.d(ContentValues.TAG, "onStart : Add Products Fragments")
    }

    override fun onResume() {
        super.onResume()
        Log.d(ContentValues.TAG, "onResume : Add Products Fragments")
    }

    override fun onPause() {
        super.onPause()
        Log.d(ContentValues.TAG, "onPause : Add Products Fragments")
    }

    override fun onStop() {
        super.onStop()
        Log.d(ContentValues.TAG, "onStop : Add Products Fragments")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(ContentValues.TAG, "onDestroyView : Add Products Fragments")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(ContentValues.TAG, "onDestroy : Add Products Fragments")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(ContentValues.TAG, "onDetach : Add Products Fragments")
    }
}