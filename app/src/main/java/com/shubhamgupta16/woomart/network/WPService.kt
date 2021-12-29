package com.shubhamgupta16.woomart.network

import com.shubhamgupta16.woomart.Config
import com.shubhamgupta16.woomart.network.wpmodel.JWTModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface WPService {

    @FormUrlEncoded
    @POST("token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun validateAuthToken(
        @Field("username") username: String, @Field("password") password: String,
    ): Call<JWTModel>

    /*@FormUrlEncoded
    @POST(".")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun validateSimpleAuth(
        @Field("email") email: String ,
        @Field("password") password: String ,
        @Field("rest_route") rest_route: String = "/simple-jwt-login/v1/auth",
        @Field("AUTH_KEY") AUTH_KEY: String = "key"
    ): Call<JWTModel>*/

    companion object {
        private const val JWT_API_PATH = "/wp-json/jwt-auth/v1/"
        private var apiService: WPService? = null

        fun getInstance(): WPService {
            if (apiService == null) {
                val client = OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .retryOnConnectionFailure(true)
                    .addInterceptor {
                        val original = it.request()
                        val request = original.newBuilder()
                            .method(original.method(), original.body())
                            .build()
                        return@addInterceptor it.proceed(request)
                    }
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl("${Config.siteUrl}$JWT_API_PATH")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                apiService = retrofit.create(WPService::class.java)
            }
            return apiService!!
        }
    }
}