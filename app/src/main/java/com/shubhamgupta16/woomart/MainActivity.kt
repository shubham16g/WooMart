package com.shubhamgupta16.woomart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.shubhamgupta16.woomart.ui.home.HomeActivity
import com.shubhamgupta16.woomart.ui.onboarding.AnonymousSignInActivity
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(baseContext, HomeActivity::class.java))
        } else {
            startActivity(Intent(baseContext, AnonymousSignInActivity::class.java))
        }
        finish()
    }
}