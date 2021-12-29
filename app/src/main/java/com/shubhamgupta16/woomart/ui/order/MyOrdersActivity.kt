package com.shubhamgupta16.woomart.ui.order

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_my_orders.*
import kotlinx.android.synthetic.main.content_my_orders.*
import kotlinx.android.synthetic.main.state_empty.*
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.viewmodels.OrderViewModel
import com.shubhamgupta16.woomart.adapter.OrderAdapter
import me.gilo.woodroid.callback.Status
import com.shubhamgupta16.woomart.common.activity.WooDroidActivity
import com.shubhamgupta16.woomart.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import me.gilo.woodroid.models.Order
@AndroidEntryPoint
class MyOrdersActivity : WooDroidActivity() {


    val viewModel: OrderViewModel by viewModels()
    var orders: ArrayList<Order> = ArrayList()

    lateinit var adapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)
        setSupportActionBar(toolbar)

        title = "My Orders"

        val layoutManager = LinearLayoutManager(
            baseContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        rvOrders.layoutManager = layoutManager
        rvOrders.isNestedScrollingEnabled = false

        orders = ArrayList()

        adapter = OrderAdapter(orders)
        rvOrders.adapter = adapter

        orders()

        llEmptyState_layout.visibility = View.GONE

    }

    private fun showEmpty(title : String, description : String) {
        tvEmptyState_title.text = title
        tvEmptyState_description.text = description

        llEmptyState_layout.visibility = View.VISIBLE

        bEmptyState_action.setOnClickListener{
            startActivity(Intent(baseContext, HomeActivity::class.java))
        }
    }

    private fun orders() {
        viewModel.orders().observe(this, androidx.lifecycle.Observer { response ->
            when (response!!.status()) {
                Status.LOADING -> {
                    showLoading()
                }

                Status.SUCCESS -> {
                    orders.clear()
                    stopShowingLoading()

                    for (order in response.data()) {
                        orders.add(order)

                    }

                    adapter.notifyDataSetChanged()

                }

                Status.ERROR -> {
                    stopShowingLoading()
                }

                Status.EMPTY -> {
                    stopShowingLoading()

                    showEmpty("No orders", "Seems like you haven't ordered anything yet!")
                }
            }

        })

    }
}
