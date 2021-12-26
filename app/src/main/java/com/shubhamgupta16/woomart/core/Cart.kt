package com.shubhamgupta16.woomart.core


import com.shubhamgupta16.woomart.core.lines.FeeLine
import com.shubhamgupta16.woomart.core.lines.LineItem
import com.shubhamgupta16.woomart.core.lines.ShippingLine
import com.shubhamgupta16.woomart.core.lines.TaxLine
import java.util.ArrayList
import java.util.Date


data class Cart(
    var id: Int = 0,
    var dateCreated: Date = Date(),
    var dateUpdate: Date = Date(),
    var completedAt: Date = Date(),

    var currency: String = "USD",

    var total: Float = 0f,
    var subtotal: Float = 0f,
    var totalLineItemsQuantity: Float = 0f,
    var totalTax: Float = 0f,
    var totalShipping: Float = 0f,
    var cartTax: Float = 0f,
    var shippingTax: Float = 0f,
    var totalDiscount: Float = 0f,
    var lineItems: MutableList<LineItem> = ArrayList(),
    var shippingLines: List<ShippingLine> = ArrayList(),

    var taxLines: List<TaxLine> = ArrayList(),

    var feeLines: List<FeeLine> = ArrayList(),

    var couponLines: List<Any> = ArrayList()

)
