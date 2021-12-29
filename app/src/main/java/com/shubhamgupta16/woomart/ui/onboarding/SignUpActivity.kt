package com.shubhamgupta16.woomart.ui.onboarding

import androidx.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.content_sign_up.*
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.sessions.CurrentSession
import com.shubhamgupta16.woomart.ui.state.ProgressDialogFragment
import me.gilo.woodroid.callback.Status

import com.shubhamgupta16.woomart.common.activity.WooDroidActivity
import com.shubhamgupta16.woomart.ui.home.HomeActivity
import com.shubhamgupta16.woomart.viewmodels.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint
import me.gilo.woodroid.models.Customer
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

@AndroidEntryPoint
class SignUpActivity : WooDroidActivity() {

    val viewModel : CustomerViewModel by viewModels()
    @Inject lateinit var currentSession: CurrentSession
    val TAG = this::getLocalClassName

    private lateinit var progressDialog: ProgressDialogFragment
    private val pattern = Pattern.compile(EMAIL_PATTERN)
    private var matcher: Matcher? = null

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        title = "Sign Up"

        flSignup.setOnClickListener{
            signUp()
        }

    }

    private fun signUp() {
        if (validates()) {
            val email = etEmail.text.toString()
            val firstName =  etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val password = etPassword.text.toString()

            val user = Customer()
            user.email = email
            user.username = email
            user.firstName = firstName
            user.lastName = lastName
            user.password = password

           viewModel.create(user).observe(this, Observer {
                   response->
               when (response!!.status()){
                   Status.LOADING ->{
                       showLoading("Uploading account details", "This will only take a short while")
                   }

                   Status.SUCCESS ->{
                       stopShowingLoading()
//                       todo login process can be done in background.
                       Toast.makeText(this, "Login to Continue", Toast.LENGTH_SHORT).show()
                       startActivity(Intent(baseContext, SignInActivity::class.java))
                       finish()
                   }

                   Status.ERROR ->{
                       stopShowingLoading()
                       Toast.makeText(baseContext, response.error().message.toString(), Toast.LENGTH_LONG).show()
                   }

                   Status.EMPTY ->{

                   }

               }
           })



        } else {
            Toast.makeText(this, "Please correct the information entered", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendDetails() {
        if (validates()) {
            val email = etEmail.text.toString()
           val firstName =  etFirstName.text.toString()
            val lastName = etLastName.text.toString()

           var customer = Customer()
            customer.email = email
            customer.firstName = firstName
            customer.lastName = lastName

            viewModel.update(currentSession.getCurrentUserId(), customer).observe(this, Observer {
                response->
                when (response!!.status()){
                    Status.LOADING ->{
                        showLoading("Uploading account details", "This will only take a short while")
                    }

                    Status.SUCCESS ->{
                        stopShowingLoading()
                        startActivity(Intent(baseContext, HomeActivity::class.java))
                    }

                    Status.ERROR ->{
                        stopShowingLoading()
                       Toast.makeText(baseContext, response.error().message.toString(), Toast.LENGTH_LONG).show()
                    }

                    Status.EMPTY ->{

                    }

                }
            })



        } else {
            Toast.makeText(this, "Please correct the information entered", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validates(): Boolean {
        tilEmail.isErrorEnabled = false
        tilFirstName.isErrorEnabled = false
        tilLastName.isErrorEnabled = false
        tilPassword.isErrorEnabled = false
        tilPasswordVerify.isErrorEnabled = false

        var validation = true

        val email = tilEmail.editText!!.text.toString()
        val firstName =  etFirstName.text.toString()
        val lastName = etLastName.text.toString()
        val password = etPassword.text.toString()
        val passwordVerify = etPasswordVerify.text.toString()



        if (!validateEmail(email)) {
            tilEmail.error = "Not a valid email address!"
            validation = false
        }

        if (firstName.isEmpty()) {
            tilFirstName.error = "Please fill this"
            validation = false
        }

        if (lastName.isEmpty()) {
            tilLastName.error = "Please fill this"
            validation = false
        }

        if (password.isEmpty()) {
            tilPassword.error = "Please fill this"
            validation = false
        }

        if (passwordVerify.isEmpty()) {
            tilPasswordVerify.error = "Please fill this"
            validation = false
        }

        if (passwordVerify != password) {
            tilPasswordVerify.error = "Passwords do not match"
            validation = false
        }

        return validation
    }

    private fun validateEmail(email: String): Boolean {
        matcher = pattern.matcher(email)
        return matcher!!.matches()
    }

    companion object {
        private const val EMAIL_PATTERN =
            "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$"
    }

}
