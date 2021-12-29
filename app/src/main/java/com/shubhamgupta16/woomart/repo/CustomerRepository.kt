package com.shubhamgupta16.woomart.repo


import com.shubhamgupta16.woomart.common.WooLiveData
import com.shubhamgupta16.woomart.network.WPService
import com.shubhamgupta16.woomart.network.wpmodel.JWTModel
import me.gilo.woodroid.Woocommerce
import me.gilo.woodroid.models.Customer
import me.gilo.woodroid.models.filters.CustomerFilter
import javax.inject.Inject

class CustomerRepository @Inject
constructor() {

    @Inject
    lateinit var woocommerce: Woocommerce

    fun create(customer: Customer): WooLiveData<Customer> {
        val callBack = WooLiveData<Customer>()
        woocommerce.CustomerRepository().create(customer).enqueue(callBack)
        return callBack
    }

    fun currentCustomer(): WooLiveData<List<Customer>> {
        val callBack = WooLiveData<List<Customer>>()
        val customerFilter = CustomerFilter()
//        todo firebase auth
//        customerFilter.setEmail(FirebaseAuth.getInstance().currentUser!!.email!!)

        woocommerce.CustomerRepository().customers(customerFilter).enqueue(callBack)
        return callBack
    }


    fun customer(id: Int): WooLiveData<Customer> {
        val callBack = WooLiveData<Customer>()
        woocommerce.CustomerRepository().customer(id).enqueue(callBack)
        return callBack
    }

    fun customers(): WooLiveData<List<Customer>> {
        val callBack = WooLiveData<List<Customer>>()

        woocommerce.CustomerRepository().customers().enqueue(callBack)

        return callBack
    }

    fun customers(customerFilter: CustomerFilter): WooLiveData<List<Customer>> {
        val callBack = WooLiveData<List<Customer>>()
        woocommerce.CustomerRepository().customers(customerFilter).enqueue(callBack)
        return callBack
    }

    fun update(id: Int, customer: Customer): WooLiveData<Customer> {
        val callBack = WooLiveData<Customer>()
        woocommerce.CustomerRepository().update(id, customer).enqueue(callBack)

        return callBack
    }

    fun delete(id: Int): WooLiveData<Customer> {
        val callBack = WooLiveData<Customer>()
        woocommerce.CustomerRepository().delete(id).enqueue(callBack)

        return callBack
    }

    fun delete(id: Int, force: Boolean): WooLiveData<Customer> {
        val callBack = WooLiveData<Customer>()
        woocommerce.CustomerRepository().delete(id, force).enqueue(callBack)

        return callBack
    }


    /**
     * The below functions uses JWTLogin Plugin in Wordpress to handle auth
     *
    * */

    fun login(email:String, password:String): WooLiveData<JWTModel> {
        val callBack = WooLiveData<JWTModel>()
//        WPService can be initialized using Hilt if multiple instances required
        WPService.getInstance().validateAuthToken(email, password).enqueue(callBack)
        return callBack
    }


}
