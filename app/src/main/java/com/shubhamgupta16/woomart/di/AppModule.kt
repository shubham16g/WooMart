package com.shubhamgupta16.woomart.di

import com.shubhamgupta16.woomart.Config
import com.shubhamgupta16.woomart.WcApp
import dagger.Module
import dagger.Provides
import me.gilo.woodroid.Woocommerce
import javax.inject.Singleton

@Module
class AppModule {

    internal var app: WcApp? = null

    internal fun AppModule(application: WcApp) {
        app = application
    }

    @Provides
    @Singleton
    internal fun providesApplication(): WcApp {
        return app!!
    }

    @Provides
    @Singleton
    internal fun providesWoocommerce(): Woocommerce {

        return Woocommerce.Builder()
            .setSiteUrl(Config.siteUrl)
            .setApiVersion(Woocommerce.API_V3)
            .setConsumerKey(Config.consumerKey)
            .setConsumerSecret(Config.consumerSecret)
            .build()
    }


//    @Provides
//    @Singleton
//    internal fun providesRoomCartRepository(): RoomCartRepository = RoomCartRepository(app!!.baseContext)



}
