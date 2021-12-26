package com.shubhamgupta16.woomart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.adapter.viewholder.ProductReviewViewHolder
import me.gilo.woodroid.models.ProductReview

class ProductReviewAdapter(private val reviews: List<ProductReview>) : RecyclerView.Adapter<ProductReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductReviewViewHolder {
        return ProductReviewViewHolder(
            parent.context,
            LayoutInflater.from(parent.context).inflate(R.layout.single_product_review, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductReviewViewHolder, position: Int) {
        holder.renderView(reviews[position])
    }


    override fun getItemCount(): Int {
        return if (reviews.isEmpty()) 0 else reviews.size
    }
}
