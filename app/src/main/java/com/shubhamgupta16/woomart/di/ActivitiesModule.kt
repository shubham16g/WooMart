package com.shubhamgupta16.woomart.di

import com.shubhamgupta16.woomart.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.shubhamgupta16.woomart.ui.onboarding.SignInActivity
import com.shubhamgupta16.woomart.ui.onboarding.SignUpActivity
import com.shubhamgupta16.woomart.ui.customer.BasicCustomerDetailsActivity
import com.shubhamgupta16.woomart.ui.customer.BillingAddressActivity
import com.shubhamgupta16.woomart.ui.customer.ProfileActivity
import com.shubhamgupta16.woomart.ui.customer.ShippingAddressActivity
import com.shubhamgupta16.woomart.ui.home.HomeActivity
import com.shubhamgupta16.woomart.ui.onboarding.AnonymousSignInActivity
import com.shubhamgupta16.woomart.ui.order.MyOrdersActivity
import com.shubhamgupta16.woomart.ui.order.OrderActivity
import com.shubhamgupta16.woomart.ui.product.CartActivity
import com.shubhamgupta16.woomart.ui.product.ProductActivity
import com.shubhamgupta16.woomart.ui.product.ShopActivity

@Module
internal abstract class ActivitiesModule {

    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributesShopActivity(): ShopActivity

    @ContributesAndroidInjector
    internal abstract fun contributesProductActivity(): ProductActivity

    @ContributesAndroidInjector
    internal abstract fun contributesHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun contributesSignInActivity(): SignInActivity

    @ContributesAndroidInjector
    internal abstract fun contributesSignUpActivity(): SignUpActivity

    @ContributesAndroidInjector
    internal abstract fun contributesBasicCustomerDetailsActivity(): BasicCustomerDetailsActivity

    @ContributesAndroidInjector
    internal abstract fun contributesBillingAddressActivity(): BillingAddressActivity

    @ContributesAndroidInjector
    internal abstract fun contributesShippingAddressActivity(): ShippingAddressActivity

    @ContributesAndroidInjector
    internal abstract fun contributesCartActivity(): CartActivity

    @ContributesAndroidInjector
    internal abstract fun contributesProfileActivity(): ProfileActivity

    @ContributesAndroidInjector
    internal abstract fun contributesAnonymousSignInActivity(): AnonymousSignInActivity

    @ContributesAndroidInjector
    internal abstract fun contributesMyOrdersActivity(): MyOrdersActivity

    @ContributesAndroidInjector
    internal abstract fun contributesOrderActivity(): OrderActivity

}
