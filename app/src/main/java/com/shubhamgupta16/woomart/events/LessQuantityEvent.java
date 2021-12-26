package com.shubhamgupta16.woomart.events;


import com.shubhamgupta16.woomart.models.CartLineItem;

public class LessQuantityEvent {

    CartLineItem cartLineItem;

    public LessQuantityEvent(CartLineItem cartLineItem) {
        this.cartLineItem = cartLineItem;
    }

    public CartLineItem getCartLineItem() {
        return cartLineItem;
    }

    public void setCartLineItem(CartLineItem cartLineItem) {
        this.cartLineItem = cartLineItem;
    }
}
