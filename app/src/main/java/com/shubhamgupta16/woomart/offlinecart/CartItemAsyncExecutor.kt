package com.shubhamgupta16.woomart.offlinecart

import android.os.AsyncTask
import com.shubhamgupta16.woomart.core.CartItemCore


class InsertCartItem(
    private val appDatabase: AppDatabase,
    private val cartItemCore: CartItemCore
) :
    AsyncTask<Void, Void, Long?>() {

    override fun doInBackground(vararg voids: Void): Long? {

        return appDatabase.cartItemDao()
            .insert(cartItemCore.toCartItemEntity())
    }
}

class UpdateCartItem(
    private val appDatabase: AppDatabase,
    private val cartItemCore: CartItemCore
) :
    AsyncTask<Void, Void, Unit>() {

    override fun doInBackground(vararg voids: Void) {
        return appDatabase.cartItemDao().update(cartItemCore.toCartItemEntity())
    }
}

class DeleteCartItem(
    private val appDatabase: AppDatabase,
    private val cartItemCore: CartItemCore
) :
    AsyncTask<Void, Void, Unit>() {

    override fun doInBackground(vararg voids: Void) {
        return appDatabase.cartItemDao().delete(cartItemCore.toCartItemEntity())
    }
}
