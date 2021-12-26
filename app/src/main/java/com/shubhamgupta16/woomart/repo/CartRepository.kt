package com.shubhamgupta16.woomart.repo


import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.shubhamgupta16.woomart.common.CompletionGenericLiveData
import com.shubhamgupta16.woomart.common.QueryLiveData
import com.shubhamgupta16.woomart.models.CartLineItem
import me.gilo.woodroid.Woocommerce
import javax.inject.Inject

open class CartRepository @Inject
constructor() {

    @Inject
    lateinit var woocommerce: Woocommerce


    private val cart: CollectionReference = FirebaseFirestore.getInstance()
        .collection("users")
        .document(FirebaseAuth.getInstance().currentUser?.uid ?: "0")
        .collection("cart")

    fun cart(): QueryLiveData<CartLineItem> {
        return QueryLiveData(
            cart,
            CartLineItem::class.java
        )
    }

    fun deleteItem(cartLineItem: CartLineItem): CompletionGenericLiveData<Void> {
        val completion = CompletionGenericLiveData<Void>()
        cart.document(cartLineItem.getId()).delete().addOnCompleteListener(completion)

        return completion
    }

    fun setQuantity(cartLineItem: CartLineItem, quantity: Int): CompletionGenericLiveData<Void> {
        val completion = CompletionGenericLiveData<Void>()
        cartLineItem.setQuantity(quantity)

        cart.document(cartLineItem.getId()).set(cartLineItem).addOnCompleteListener(completion)

        return completion
    }

    fun deleteItems(): CompletionGenericLiveData<Void> {
        val completion = CompletionGenericLiveData<Void>()
        deleteCartItems().addOnCompleteListener(completion)
        return completion
    }


    private fun deleteCartItems(): Task<Void> {
        return cart.firestore.runTransaction {
            cart.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        cart.document(document.id).delete()
                    }

                } else {
                }
            }

            null
        }
    }

}
