package com.shubhamgupta16.woomart.adapter.viewholder

import android.content.Context
import android.content.Intent
import android.text.Html
import android.text.SpannableString
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.events.AddQuantityEvent
import com.shubhamgupta16.woomart.events.LessQuantityEvent
import com.shubhamgupta16.woomart.models.CartLineItem
import com.shubhamgupta16.woomart.ui.product.ProductActivity
import org.greenrobot.eventbus.EventBus


class CartViewHolder(val context: Context, itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun renderView(cartLineItem: CartLineItem) {
        val ivImage = itemView.findViewById<ImageView>(R.id.ivImage)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)

        val tvAdd = itemView.findViewById<TextView>(R.id.tvAdd)
        val tvQty = itemView.findViewById<TextView>(R.id.tvQty)
        val tvLess = itemView.findViewById<TextView>(R.id.tvReduce)


        tvQty.text = "" + cartLineItem.quantity

        var product = cartLineItem.product

        tvTitle.text = cartLineItem.name
        tvDescription.text = Html.fromHtml(product.description)

        if (product.images != null && product.images.isNotEmpty()){
            Picasso.with(context).load(product.images[0].src).into(ivImage)
        }

        tvTitle.text = product.name

        val regularPrice = product.regular_price
        val salePrice = product.sale_price

        if (product.isOn_sale) {
            tvPrice.text = SpannableString("Ksh$salePrice")
        }else{
            tvPrice.text = SpannableString("Ksh$regularPrice")
        }


        itemView.setOnClickListener{
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra("productId", cartLineItem.productId)

            context.startActivity(intent)
        }

        tvAdd.setOnClickListener{
            EventBus.getDefault().post(AddQuantityEvent(cartLineItem))
        }

        tvLess.setOnClickListener{
            EventBus.getDefault().post(LessQuantityEvent(cartLineItem))
        }

    }


}