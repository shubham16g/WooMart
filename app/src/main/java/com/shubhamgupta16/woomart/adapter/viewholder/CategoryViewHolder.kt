package com.shubhamgupta16.woomart.adapter.viewholder

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.ui.product.ShopActivity
import me.gilo.woodroid.models.Category

class CategoryViewHolder(val context: Context, itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun renderView(category: Category) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

        tvTitle.text = category.name

        itemView.setOnClickListener{
            val intent = Intent(context, ShopActivity::class.java)
            intent.putExtra("categoryId",category.id)
            intent.putExtra("name",category.name)

            context.startActivity(intent)
        }
    }


}