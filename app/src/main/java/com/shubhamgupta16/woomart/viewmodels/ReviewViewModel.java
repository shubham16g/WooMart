package com.shubhamgupta16.woomart.viewmodels;

import androidx.lifecycle.ViewModel;

import com.shubhamgupta16.woomart.common.WooLiveData;
import com.shubhamgupta16.woomart.repo.ReviewRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import me.gilo.woodroid.models.ProductReview;


@HiltViewModel
public final class ReviewViewModel extends ViewModel {
    private final ReviewRepository reviewRepository;

    @Inject
    ReviewViewModel(ReviewRepository reviewRepository) {
        this.reviewRepository =  reviewRepository;
    }

    public WooLiveData<List<ProductReview>> reviews(int productId) {
        return reviewRepository.reviews(productId);
    }

    public WooLiveData<ProductReview> create(ProductReview review) {
        return reviewRepository.create(review);
    }

}