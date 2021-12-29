package com.shubhamgupta16.woomart.common

import android.util.Log
import androidx.lifecycle.LiveData
import me.gilo.woodroid.callback.NetworkException
import me.gilo.woodroid.callback.Resource
import me.gilo.woodroid.callback.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class WooLiveData<T> : LiveData<Resource<T>?>(), Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        Log.d("TAG", "onResponse: $response")
        if (response.isSuccessful) {
            Log.d("TAG", "onResponse: " + response.body().toString())
            setValue(Resource(response.body()!!))
        } else {
            var error: String? = null
            try {
                error = response.errorBody()!!.string()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (error == null) {
                error = "Something went wrong"
            }
            setValue(Resource(NetworkException(error)))
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        value = Resource(NetworkException(t))
    }

    init {
        value = Resource(Status.LOADING)
    }
}