package com.shubhamgupta16.woomart.ui.customer

import androidx.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.content_profile.*
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.viewmodels.CustomerViewModel
import com.shubhamgupta16.woomart.ui.WooDroidActivity
import dagger.hilt.android.AndroidEntryPoint
import me.gilo.woodroid.callback.Status
@AndroidEntryPoint
class ProfileActivity : WooDroidActivity<CustomerViewModel>() {

    val  viewModel : CustomerViewModel by viewModels()
//    fixme it was initialize in onResume..

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbar)

        title = "Profile"

        tvBasicDetailsEdit.setOnClickListener{startActivity(Intent(baseContext, BasicCustomerDetailsActivity::class.java))}
        tvBillingAddressEdit.setOnClickListener{startActivity(Intent(baseContext, BillingAddressActivity::class.java))}
        tvShippingAddressEdit.setOnClickListener{startActivity(Intent(baseContext, ShippingAddressActivity::class.java))}

    }

    override fun onResume() {
        super.onResume()

        customer()
    }


    private fun customer() {
        viewModel.currentCustomer().observe(this, Observer {
                response->
            when (response!!.status()){
                Status.LOADING ->{
                    showLoading("Retrieve customer details", "This will only take a short while")
                }

                Status.SUCCESS ->{
                    stopShowingLoading()
                    var customer = response.data()[0]

                    tvBasicDetailsName.text = customer.firstName + " " + customer.lastName

                    tvEmail.text = "Email : " + customer.email
                    tvUsername.text = "Username : " + customer.username

                    tvShippingAddress.text = customer.shippingAddress.toString()
                    tvBillingAddress.text = customer.billingAddress.toString()

                }

                Status.ERROR ->{
                    stopShowingLoading()
                    Toast.makeText(baseContext, response.error().message.toString(), Toast.LENGTH_LONG).show()
                }

                Status.EMPTY ->{
                    stopShowingLoading()
                }

            }
        })

    }

}
