package com.shubhamgupta16.woomart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.adapter.viewholder.ProductViewHolder
import me.gilo.woodroid.models.Product

class HomeProductAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            parent.context,
            LayoutInflater.from(parent.context).inflate(R.layout.single_home_product_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.renderView(products[position])
    }


    override fun getItemCount(): Int {
        return if (products.isEmpty()) 0 else products.size
    }
}
