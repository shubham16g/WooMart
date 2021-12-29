package com.shubhamgupta16.woomart.sessions

import android.content.Context
import android.content.SharedPreferences
import com.shubhamgupta16.woomart.network.wpmodel.JWTData
import com.shubhamgupta16.woomart.network.wpmodel.JWTModel
import dagger.hilt.android.qualifiers.ApplicationContext
import me.gilo.woodroid.models.Customer
import javax.inject.Inject

class CurrentSession @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        const val PREF_NAME = "3userApp"
    }

    private var pref: SharedPreferences = context.getSharedPreferences(
        PREF_NAME,
        Context.MODE_PRIVATE
    )

    private val editor
        get() = pref.edit()

    fun login(model: JWTData, password: String){
        login(model.id, model.email, model.displayName, model.firstName, model.lastName,
            model.token, password)
    }

    fun login(
        id: Int, email: String, displayName: String, firstName: String, lastName: String,
        token: String, password: String
    ) {
        editor.putString("displayName", displayName)
            .putString("firstName", firstName)
            .putString("lastName", lastName)
            .putString("token", token)
            .putString("email", email)
            .putInt("cid", id)
            .putString("password", password)
            .apply()
    }

    fun updateCustomer(customer: Customer) {
        editor.putString("firstName", customer.firstName).putString("lastName", customer.lastName)
            .apply()
    }

    fun getCurrentUserId(): Int {
        return pref.getInt("cid", 0)
    }

    fun getCurrentUserEmail(): String {
        return pref.getString("email", null).toString()
    }

    fun getUserPassword(): String {
        return pref.getString("password", "").toString()
    }

    fun hasCurrentUser(): Boolean {
        return getCurrentUserId() != 0
    }


}