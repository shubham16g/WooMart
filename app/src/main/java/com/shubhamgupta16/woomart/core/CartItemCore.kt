package com.shubhamgupta16.woomart.core


data class CartItemCore (
    var id: Int? = 0,
    var createdAt: Long? = null,
    var modifiedAt: Long? = null,
    var productId: Int = 0,
    var variationId: Int? = 0,
    var quantity: Int = 0,

    var productName: String? = "",
    var productPrice: String? = "",
    var productImage: String? = ""

)