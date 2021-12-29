package com.shubhamgupta16.woomart.viewmodels

import androidx.lifecycle.ViewModel
import com.shubhamgupta16.woomart.common.WooLiveData
import com.shubhamgupta16.woomart.network.wpmodel.JWTModel
import com.shubhamgupta16.woomart.repo.CustomerRepository
import com.shubhamgupta16.woomart.sessions.CurrentSession
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gilo.woodroid.models.Customer
import me.gilo.woodroid.models.filters.CustomerFilter
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject internal constructor(
    private val customerRepository: CustomerRepository
) : ViewModel() {

    fun create(customer: Customer?): WooLiveData<Customer> {
        return customerRepository.create(customer!!)
    }

    fun customer(id: Int): WooLiveData<Customer> {
        return customerRepository.customer(id)
    }

    fun currentCustomer(): WooLiveData<List<Customer>> {
        return customerRepository.currentCustomer()
    }

    fun customers(): WooLiveData<List<Customer>> {
        return customerRepository.customers()
    }

    fun customers(customerFilter: CustomerFilter?): WooLiveData<List<Customer>> {
        return customerRepository.customers(customerFilter!!)
    }

    fun customer(customerFilter: CustomerFilter?): WooLiveData<List<Customer>> {
        return customerRepository.customers(customerFilter!!)
    }

    fun update(id: Int, customer: Customer?): WooLiveData<Customer> {
        return customerRepository.update(id, customer!!)
    }

    fun delete(id: Int): WooLiveData<Customer> {
        return customerRepository.delete(id)
    }

    fun delete(id: Int, force: Boolean): WooLiveData<Customer> {
        return customerRepository.delete(id, force)
    }

    fun login(email: String?, password: String?): WooLiveData<JWTModel> {
        return customerRepository.login(email!!, password!!)
    }
}