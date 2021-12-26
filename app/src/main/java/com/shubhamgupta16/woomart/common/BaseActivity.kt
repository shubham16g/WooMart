package com.shubhamgupta16.woomart.common

import android.annotation.SuppressLint
import android.content.Context
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import io.github.inflationx.viewpump.ViewPumpContextWrapper

/**
 * This Activity is to be inherited by any activity to initiate the injection.
 */
@SuppressLint("Registered")
open class BaseActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    fun <T : ViewModel> getViewModel(cls: Class<T>): T {
        return viewModelFactory.create(cls)
    }

    public override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }
}