package com.shubhamgupta16.woomart.offlinecart

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Room
import com.shubhamgupta16.woomart.core.Cart
import com.shubhamgupta16.woomart.core.CartItemCore
import com.shubhamgupta16.woomart.offlinecart.*
import com.shubhamgupta16.woomart.offlinecart.utils.AppUtils

class RoomCartRepository(val context: Context){

    private val appDatabase: AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, Config.DB_NAME)
        .fallbackToDestructiveMigration()
        .build()


    fun addToCart(cartItemCore: CartItemCore) {
        if (cartItemCore.createdAt == null) {
            cartItemCore.createdAt = AppUtils.currentDateTime.time
        }

        InsertCartItem(appDatabase, cartItemCore).execute()
    }



    fun update(cartItemCore: CartItemCore) {
        cartItemCore.modifiedAt = AppUtils.currentDateTime.time
        UpdateCartItem(appDatabase, cartItemCore).execute()

    }

    fun count(): LiveData<Int> {
        return appDatabase.cartItemDao().count()
    }

    fun exists(productId: Int): LiveData<Boolean> {
        return appDatabase.cartItemDao().exists(productId)
    }

    fun delete(cartItemCore: CartItemCore) {
        DeleteCartItem(appDatabase, cartItemCore).execute()
    }

    fun cartItem(id: Int): LiveData<CartItemCore> {
        return Transformations.map(
            appDatabase.cartItemDao().get(id)
        ) { cartItemEntity: CartItemEntity? -> cartItemEntity?.toCartItem()
        }
    }

    /*fun cart(): Cart {
        return Cart(
            items = Transformations.map(appDatabase.cartItemDao().items()){
                it.map { cartItemEntity -> cartItemEntity.toCartItem() }
            }
        )
    }*/

}
