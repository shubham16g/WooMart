package com.shubhamgupta16.woomart.events;


import com.shubhamgupta16.woomart.models.CartLineItem;

public class AddQuantityEvent {

    CartLineItem cartLineItem;

    public AddQuantityEvent(CartLineItem cartLineItem) {
        this.cartLineItem = cartLineItem;
    }

    public CartLineItem getCartLineItem() {
        return cartLineItem;
    }

    public void setCartLineItem(CartLineItem cartLineItem) {
        this.cartLineItem = cartLineItem;
    }
}
