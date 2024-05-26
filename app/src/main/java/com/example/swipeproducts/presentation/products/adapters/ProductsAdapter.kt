package com.example.swipeproducts.presentation.products.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.swipeproducts.R
import com.example.swipeproducts.databinding.IvProductsBinding
import com.example.swipeproducts.domain.models.Product
import com.example.swipeproducts.utils.FilteringProducts

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() , Filterable {

    class ProductsViewHolder(val binding : IvProductsBinding) : ViewHolder(binding.root)

    // diffutils for differentiating between two list in recycler view
    val diffUtil = object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.tax == newItem.tax
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffUtil) // doing diffutils work asynchronously

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(IvProductsBinding.inflate(LayoutInflater.from(parent.context) , parent ,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.binding.apply {

            ivProduct.let {
                if(product.image == ""){
                    it.load(R.drawable.placeholder)
                }
                else{
                    it.load(product.image){placeholder(R.drawable.placeholder)}
                }
            }
            tvProductName.text = product.product_name
            tvProductPrice.text = "₹${product.price}"
            tvProductType.text = product.product_type
            tvProductTax.text = product.tax.toString()
        }
    }

    private val filter : FilteringProducts? = null
    var originalList = ArrayList<Product>()
    override fun getFilter(): Filter {
        if(filter == null) return FilteringProducts(this,originalList)
        return filter
    }
}