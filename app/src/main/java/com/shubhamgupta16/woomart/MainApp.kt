package com.shubhamgupta16.woomart

import android.app.Application
import me.gilo.woodroid.Woocommerce

class MainApp : Application() {

    companion object {
        const val CONSUMER_KEY = "ck_3dcccd107915bfbd7fbed52cb9297a915f338439";
        const val CONSUMER_SECRET = "cs_9dc967fbe276a7167d86465df64e535976c94c4e";
        const val BASE_URL = "http://b837-2409-4063-6e80-d876-991a-2620-608c-c59b.ngrok.io/woomart"
        fun Application.getWooCommerce(): Woocommerce {
            return (this as MainApp).woocommerce
        }
    }

    private lateinit var woocommerce: Woocommerce


    override fun onCreate() {
        super.onCreate()
        woocommerce = Woocommerce.Builder()
            .setSiteUrl(BASE_URL)
            .setApiVersion(Woocommerce.API_V2)
            .setConsumerKey(CONSUMER_KEY)
            .setConsumerSecret(CONSUMER_SECRET)
            .build()
    }

}