package com.shubhamgupta16.woomart.common.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubhamgupta16.woomart.R

import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.content_menu.*
import com.shubhamgupta16.woomart.adapter.MenuAdapter
import java.util.ArrayList

class MenuActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setSupportActionBar(toolbar)

        title = "Menu"

        val layoutManager = LinearLayoutManager(
            baseContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        rvMenu.layoutManager = layoutManager
        rvMenu.isNestedScrollingEnabled = false

        var titles = ArrayList<String>()
        titles.add("Products")
        titles.add("Coupons")

        var adapter = MenuAdapter(titles)
        rvMenu.adapter = adapter

    }

}
