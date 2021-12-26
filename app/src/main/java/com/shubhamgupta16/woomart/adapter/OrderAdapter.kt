package com.shubhamgupta16.woomart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.adapter.viewholder.OrderViewHolder
import me.gilo.woodroid.models.Order

class OrderAdapter(private val orders: List<Order>) : RecyclerView.Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            parent.context,
            LayoutInflater.from(parent.context).inflate(R.layout.single_order_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.renderView(orders[position])
    }


    override fun getItemCount(): Int {
        return if (orders.isEmpty()) 0 else orders.size
    }
}
