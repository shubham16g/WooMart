package com.shubhamgupta16.woomart.adapter.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.shubhamgupta16.woomart.ui.coupon.CouponsActivity;
import com.shubhamgupta16.woomart.ui.product.ShopActivity;
import com.shubhamgupta16.woomart.R;

public class MenuViewHolder extends RecyclerView.ViewHolder {

    View itemView;
    Context context;

    public MenuViewHolder(Context context, View itemView) {
        super(itemView);

        this.itemView = itemView;
        this.context = context;
    }

    public void renderView(String title) {
        TextView tvTitle = itemView.findViewById(R.id.tvTitle);
        tvTitle.setText(title);

        tvTitle.setOnClickListener(view -> startActivity(title));
    }

    private void startActivity(String title) {
        Intent intent;

        switch (title){
            case "Products":
                intent = new Intent(context, ShopActivity.class);
                context.startActivity(intent);
                break;
            case "Coupons":
                intent = new Intent(context, CouponsActivity.class);
                context.startActivity(intent);
                break;
        }
    }


}