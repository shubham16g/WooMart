package com.shubhamgupta16.woomart.ui.onboarding

import androidx.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.content_sign_in.*
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.ui.state.ProgressDialogFragment
import com.shubhamgupta16.woomart.viewmodels.UserViewModel
import me.gilo.woodroid.callback.Status
import com.shubhamgupta16.woomart.ui.WooDroidActivity
import com.shubhamgupta16.woomart.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Matcher
import java.util.regex.Pattern

@AndroidEntryPoint
class SignInActivity : WooDroidActivity<UserViewModel>() {


    val viewModel : UserViewModel by viewModels()

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

            viewModel.login(email, password).observe(this, Observer {
                response->
                when (response!!.status()){
                    Status.LOADING ->{
                        showLoading("Performing log in", "This will only take a short while")
                    }

                    Status.SUCCESS ->{
                        stopShowingLoading()
                        startActivity(Intent(baseContext, HomeActivity::class.java))

                    }

                    Status.ERROR ->{
                        stopShowingLoading()
                        Toast.makeText(baseContext, "Something went wrong", Toast.LENGTH_LONG).show()
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
