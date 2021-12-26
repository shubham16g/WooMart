package com.shubhamgupta16.woomart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.adapter.viewholder.MenuViewHolder

class MenuAdapter(private val titles: List<String>) : RecyclerView.Adapter<MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            parent.context,
            LayoutInflater.from(parent.context).inflate(R.layout.single_menu_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.renderView(titles[position])
    }


    override fun getItemCount(): Int {
        return if (titles.isEmpty()) 0 else titles.size
    }
}
