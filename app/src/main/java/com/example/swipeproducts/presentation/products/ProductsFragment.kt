package com.example.swipeproducts.presentation.products

import android.content.Context
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
import androidx.recyclerview.widget.RecyclerView
import com.example.swipeproducts.R
import com.example.swipeproducts.databinding.FragmentProductsBinding
import com.example.swipeproducts.domain.models.Product
import com.example.swipeproducts.presentation.products.adapters.ProductsAdapter
import com.example.swipeproducts.utils.NetworkManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProductsFragment : Fragment(), AppEntryCallback {
    private val networkManager: NetworkManager by inject { parametersOf(requireContext()) }

    private lateinit var binding: FragmentProductsBinding
    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkAppUserEntry()
        searchProducts()
        onScrollRv()
        navigateToAddProductFragment()
    }

    // hide and show fab while scrolling through recycler view
    private fun onScrollRv() {
        binding.rvProducts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 10 && binding.fabAddProducts.isShown) {
                    binding.fabAddProducts.hide()
                }
                if (dy < -10 && !binding.fabAddProducts.isShown) {
                    binding.fabAddProducts.show()
                }
                if (!recyclerView.canScrollVertically(-1)) {
                    binding.fabAddProducts.show()
                }
            }
        })
    }

    private fun checkAppUserEntry() {
        // reading whether the user has entered to the app earlier or not
        viewModel.readAppEntry(this)
    }

    private fun saveAppUserEntry() {

        networkManager.observe(viewLifecycleOwner) { hasInternet ->

//            Here we will store the app entry if and only if there is an internet.

            if (hasInternet) {
                binding.llNoInternet.visibility = View.GONE
                binding.searchCv.visibility = View.VISIBLE
                binding.ll.visibility = View.VISIBLE
                binding.fabAddProducts.visibility = View.VISIBLE
                fetchingProducts()
                showingProducts()
                Log.d("saving", "frag")
                viewModel.saveAppUserEntry()
            }
            // if user has entered earlier then it's ok , but if not then show no internet indication
            else {
                lifecycleScope.launch {
                    viewModel.appEntry.collect { isEntered ->
                        if (!isEntered) {
                            binding.llNoInternet.visibility = View.VISIBLE
                            binding.searchCv.visibility = View.GONE
                            binding.ll.visibility = View.GONE
                            binding.fabAddProducts.visibility = View.GONE
                        }
                    }
                }

            }

        }
    }


    private fun searchProducts() {
        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val query = s.toString().trim()
                if (query.isEmpty()) binding.rvProducts.scrollToPosition(0) // when user clears the search field , then user should see the first product of the list.
                productsAdapter.filter.filter(query)
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    private fun showingNoDataLottie(show: Boolean) {
        if (show) {
            binding.animationView.visibility = View.VISIBLE
        } else {
            binding.animationView.visibility = View.GONE

        }
    }


    private fun navigateToAddProductFragment() {
        binding.fabAddProducts.setOnClickListener {
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


    private fun fetchingProducts() {
        viewModel.getProductList()
    }

    override fun onAppEntryRead(isEntered: Boolean) {
        // We used this interface , for callback purpose. So that we must read  user entry first then only move ahead
        lifecycleScope.launch {
            viewModel.appEntry.collect { isEntered ->
                if (isEntered) {
                    Log.d("saving", "User has entered before")
                    fetchingProducts()
                    showingProducts()
                } else {
                    Log.d("saving", "User has not entered before, saving entry")
                    saveAppUserEntry()
                }
            }
        }

    }


}