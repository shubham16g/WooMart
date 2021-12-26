package com.shubhamgupta16.woomart.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.shubhamgupta16.woomart.Config
import com.shubhamgupta16.woomart.ui.state.ProgressDialogFragment
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import me.gilo.woodroid.Woocommerce

open class BaseActivity : AppCompatActivity() {

    private lateinit var progressDialog : ProgressDialogFragment

    val woocommerce = Woocommerce.Builder()
        .setSiteUrl(Config.siteUrl)
        .setApiVersion(Woocommerce.API_V3)
        .setConsumerKey(Config.consumerKey)
        .setConsumerSecret(Config.consumerSecret)
        .build()


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    fun showLoading(title: String, message: String) {
        val manager = supportFragmentManager
        progressDialog = ProgressDialogFragment.newInstance(title, message)
        progressDialog.isCancelable = false
        progressDialog.show(manager, "progress")
    }

    fun showLoading() {
       showLoading("This will only take a sec", "Loading")
    }

    fun stopShowingLoading() {
        progressDialog.dismiss()
    }
}
