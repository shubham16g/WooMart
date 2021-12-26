package com.shubhamgupta16.woomart.repo;


import com.shubhamgupta16.woomart.common.WooLiveData;

import java.util.List;

import javax.inject.Inject;

import me.gilo.woodroid.Woocommerce;
import me.gilo.woodroid.models.ProductReview;
import me.gilo.woodroid.models.filters.ProductReviewFilter;

public class ReviewRepository {

    @Inject
    Woocommerce woocommerce;

    @Inject
    public ReviewRepository() {
    }


    public WooLiveData<List<ProductReview>> reviews(int productId) {
        final WooLiveData<List<ProductReview>> callBack = new WooLiveData();

        ProductReviewFilter filter = new ProductReviewFilter();
        int[] products = {productId};

        filter.setProduct(products);

        woocommerce.ReviewRepository().reviews(filter).enqueue(callBack);
        return callBack;
    }

    public WooLiveData<ProductReview> create(ProductReview review) {
        final WooLiveData<ProductReview> callBack = new WooLiveData();
        woocommerce.ReviewRepository().create(review).enqueue(callBack);
        return callBack;
    }


}
