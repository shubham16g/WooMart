package com.shubhamgupta16.woomart.di


import com.shubhamgupta16.woomart.WcApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ViewModelModule::class, FirebaseModule::class, ActivitiesModule::class, AppModule::class])
internal interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: WcApp)

}
