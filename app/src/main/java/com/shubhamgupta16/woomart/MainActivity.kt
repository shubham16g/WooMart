package com.shubhamgupta16.woomart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.shubhamgupta16.woomart.network.WPService
import com.shubhamgupta16.woomart.network.wpmodel.JWTModel
import com.shubhamgupta16.woomart.sessions.CurrentSession
import com.shubhamgupta16.woomart.ui.home.HomeActivity
import com.shubhamgupta16.woomart.ui.onboarding.SignInActivity
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var currentSession: CurrentSession
    companion object{
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val wpService = WPService.getInstance()
        wpService.validateAuthToken("sam.hkdoc", "123456")
            .enqueue(object: Callback<JWTModel> {
            override fun onResponse(call: Call<JWTModel>, response: Response<JWTModel>) {
                Log.d(TAG, "onResponse: $response")
                Log.d(TAG, "onResponse: ${response.body()}")
            }
            override fun onFailure(call: Call<JWTModel>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })*/

        if (currentSession.hasCurrentUser()) {
            startActivity(Intent(baseContext, HomeActivity::class.java))
        } else {
            startActivity(Intent(baseContext, SignInActivity::class.java))
        }
        finish()
    }
}