package com.shubhamgupta16.woomart.ui.onboarding

import androidx.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.viewmodels.UserViewModel
import me.gilo.woodroid.callback.Status
import com.shubhamgupta16.woomart.ui.WooDroidActivity
import com.shubhamgupta16.woomart.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnonymousSignInActivity : WooDroidActivity<UserViewModel>() {


    companion object{
        private const val TAG = "AnonymousSignInActivity"
    }

     val viewModel: UserViewModel by viewModels()

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anonymous_sign_in)

        anonymousSignIn()

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Called")
    }

    private fun anonymousSignIn() {
        viewModel.anonymousSignIn().observe(this, Observer { response ->
            when (response!!.status()) {
                Status.LOADING -> {
                    showLoading("Setting you up with an account", "This will only take a short while")
                }

                Status.SUCCESS -> {
                    stopShowingLoading()
                    startActivity(Intent(baseContext, HomeActivity::class.java))

                }

                Status.ERROR -> {
                    stopShowingLoading()
                    Toast.makeText(baseContext, "Something went wrong555555555555555", Toast.LENGTH_LONG).show()
                }

                Status.EMPTY -> {
                    stopShowingLoading()
                }

            }
        })

    }
}