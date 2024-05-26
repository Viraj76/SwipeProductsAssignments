package com.example.swipeproducts.utils


import android.widget.Filter
import com.example.swipeproducts.domain.models.Product
import com.example.swipeproducts.presentation.products.adapters.ProductsAdapter

import java.util.Locale


// Class for searching  products

class FilteringProducts(
    private val adapter: ProductsAdapter,
    private val filter: ArrayList<Product>
) : Filter() {
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val result = FilterResults()

        if(!constraint.isNullOrEmpty()){
            val filteredList = ArrayList<Product>()
            val query = constraint.toString().trim().uppercase(Locale.getDefault()).split(" ")

            for(products in filter){
                if(query.any {
                        products.product_name.uppercase(Locale.getDefault())?.contains(it) ==true||
                                products.product_type.uppercase(Locale.getDefault())?.contains(it) ==true||
                                products.price.toString().uppercase(Locale.getDefault())?.contains(it) ==true||
                                products.tax.toString().uppercase(Locale.getDefault()).contains(it)

                    }){
                    filteredList.add(products)
                }
            }
            result.values = filteredList
            result.count = filteredList.size

        }

        else{
            result.values = filter
            result.count = filter.size
        }

        return result
    }

    override fun publishResults(p0: CharSequence?, result: FilterResults?) {
        adapter.differ.submitList(result?.values as ArrayList<Product>)
    }
}