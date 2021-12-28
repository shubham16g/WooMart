package com.shubhamgupta16.woomart.viewmodels;

import androidx.lifecycle.ViewModel;

import com.shubhamgupta16.woomart.common.CompletionGenericLiveData;
import com.shubhamgupta16.woomart.common.QueryLiveData;
import com.shubhamgupta16.woomart.common.WooLiveData;
import com.shubhamgupta16.woomart.models.CartLineItem;

import java.util.List;

import javax.inject.Inject;

import com.shubhamgupta16.woomart.repo.CartRepository;
import com.shubhamgupta16.woomart.repo.CustomerRepository;
import com.shubhamgupta16.woomart.repo.OrderRepository;

import dagger.hilt.android.lifecycle.HiltViewModel;
import me.gilo.woodroid.models.Customer;
import me.gilo.woodroid.models.Order;

@HiltViewModel
public final class CartViewModel extends ViewModel {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Inject
    CartViewModel(CartRepository cartRepository, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.cartRepository =  cartRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public QueryLiveData<CartLineItem> cart() {
        return cartRepository.cart();
    }

    public CompletionGenericLiveData<Void> deleteItem(CartLineItem cartLineItem) {
        return cartRepository.deleteItem(cartLineItem);
    }

    public CompletionGenericLiveData<Void> deleteAllCartItems() {
        return cartRepository.deleteItems();
    }

    public CompletionGenericLiveData<Void> setQuantity(CartLineItem cartLineItem, int quantity) {
        return cartRepository.setQuantity(cartLineItem, quantity);
    }

    public WooLiveData<Order> createOrder(Order order) {
        return orderRepository.create(order);
    }

    public WooLiveData<List<Customer>> currentCustomer() {
        return customerRepository.currentCustomer();
    }

}