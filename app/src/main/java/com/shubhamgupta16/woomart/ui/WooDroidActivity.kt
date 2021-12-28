package com.shubhamgupta16.woomart.ui

import androidx.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import com.shubhamgupta16.woomart.ui.onboarding.SignInActivity
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.common.BaseActivity
import com.shubhamgupta16.woomart.ui.state.ProgressDialogFragment
import com.shubhamgupta16.woomart.viewmodels.CartViewModel
import me.gilo.woodroid.callback.Status
import com.shubhamgupta16.woomart.ui.product.CartActivity

abstract class WooDroidActivity<T : ViewModel> : BaseActivity() {


    private lateinit var progressDialog: ProgressDialogFragment

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))

    }

    override fun onResume() {
        super.onResume()

        if(FirebaseAuth.getInstance().currentUser == null){
            startActivity(Intent(baseContext, SignInActivity::class.java))
            finish()
        }
        //cart()
    }

    fun showLoading() {
        showLoading("Please wait", "This will only take a second")
    }

    fun showLoading(title: String, message: String) {
        val manager = supportFragmentManager
        progressDialog = ProgressDialogFragment.newInstance(title, message)
        progressDialog.isCancelable = false
        progressDialog.show(manager, "progress")
    }

    fun stopShowingLoading() {
        progressDialog.dismiss()
    }

    fun toast(text : String){
        Toast.makeText(baseContext, text, Toast.LENGTH_LONG).show()
    }

    var tvCartCounter : TextView? = null

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.product, menu)

        val item = menu.findItem(R.id.menu_cart)
        val rootView = item.actionView as FrameLayout
        tvCartCounter = rootView.findViewById<TextView>(R.id.tvCart_counter)

        rootView.setOnClickListener{startActivity(Intent(baseContext, CartActivity::class.java))}

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_cart -> {

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    open fun cart() {
        val viewModel:CartViewModel by viewModels()

        viewModel.cart().observe(this, { response ->
            when (response!!.status()) {
                Status.LOADING -> {

                }

                Status.SUCCESS -> {
                    for (cartItem in response.data()){

                    }

                    if ( response.data().size == 0 && tvCartCounter != null){
                        tvCartCounter?.visibility = View.GONE
                    }else{
                        tvCartCounter?.visibility = View.VISIBLE
                        tvCartCounter?.text =  response.data().size.toString()
                    }


                }

                Status.ERROR -> {

                }

                Status.EMPTY -> {
                    if ( response.data().size == 0 && tvCartCounter != null){
                        tvCartCounter?.visibility = View.GONE
                    }else{
                        tvCartCounter?.visibility = View.VISIBLE
                        tvCartCounter?.text =  response.data().size.toString()
                    }
                }
            }

        })

    }

}
