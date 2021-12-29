package com.shubhamgupta16.woomart.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.sessions.CurrentSession
import com.shubhamgupta16.woomart.common.activity.WooDroidActivity
import com.shubhamgupta16.woomart.ui.home.HomeActivity
import com.shubhamgupta16.woomart.ui.state.ProgressDialogFragment
import com.shubhamgupta16.woomart.viewmodels.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.content_sign_in.*
import me.gilo.woodroid.callback.Status
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : WooDroidActivity() {


    val viewModel: CustomerViewModel by viewModels()
    @Inject
    lateinit var currentSession: CurrentSession

    private lateinit var progressDialog: ProgressDialogFragment
    private val pattern = Pattern.compile(EMAIL_PATTERN)
    private var matcher: Matcher? = null

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        title = "Sign In"

        flSignIn.setOnClickListener{
            login()
        }

        tvSignUp.setOnClickListener{startActivity(Intent(baseContext, SignUpActivity::class.java))}
    }

    private fun login() {
        if (validates()) {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            viewModel.login(email, password).observe(this, {
                response->
                when (response!!.status()){
                    Status.LOADING ->{
                        showLoading("Performing log in", "This will only take a short while")
                    }

                    Status.SUCCESS -> {
                        stopShowingLoading()
                        val jwtModel = response.data()
                        if (jwtModel.success) {
                            currentSession.login(jwtModel.data, password)
                            startActivity(Intent(baseContext, HomeActivity::class.java))
                            finish()
                        }
                        else
                            Toast.makeText(baseContext, jwtModel.message, Toast.LENGTH_SHORT).show()
                    }

                    Status.ERROR ->{
                        stopShowingLoading()
                        Toast.makeText(baseContext, "Something went wrong -> ${response.error()}", Toast.LENGTH_LONG).show()
                    }

                    Status.EMPTY ->{
                        stopShowingLoading()
                    }

                }
            })
        } else {
            Toast.makeText(this, "Please correct the information entered", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadUserData(){
        /*viewModel.customer.observe(this){

        }*/
    }

    private fun validates(): Boolean {
        var validation = true

        tilEmail.isErrorEnabled = false
        tilPassword.isErrorEnabled = false

        val email = tilEmail.editText!!.text.toString()

        if (email.isEmpty()) {
            tilEmail.error = "This cannot be left blank!"
            validation = false
        }

        return validation
    }

    private fun validateEmail(email: String): Boolean {
        matcher = pattern.matcher(email)
        return matcher!!.matches()
    }

    companion object {
        private const val EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$"
    }

}
