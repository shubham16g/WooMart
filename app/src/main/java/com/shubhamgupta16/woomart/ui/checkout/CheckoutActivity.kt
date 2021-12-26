package com.shubhamgupta16.woomart.ui.checkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shubhamgupta16.woomart.R

import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        setSupportActionBar(toolbar)
    }

}
