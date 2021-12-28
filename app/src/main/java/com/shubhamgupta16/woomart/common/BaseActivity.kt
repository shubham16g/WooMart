package com.shubhamgupta16.woomart.common

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper

/**
 * This Activity is to be inherited by any activity to initiate the injection.
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    public override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }
}