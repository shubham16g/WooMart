package com.shubhamgupta16.woomart.core

import androidx.lifecycle.LiveData


abstract class CartRepository{

    abstract fun clear(): LiveData<String>
    abstract fun count(id: Int): LiveData<Int>
    abstract fun cart(): LiveData<Cart>
    abstract fun addToCart(cartItemCore: CartItemCore)
    abstract fun delete(cartId: String): LiveData<String>
    abstract fun restore(cartId: String): LiveData<String>
    abstract fun update(cartId: String, quantity: Int)

}
