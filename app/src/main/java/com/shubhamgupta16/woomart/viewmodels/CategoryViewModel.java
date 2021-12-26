package com.shubhamgupta16.woomart.viewmodels;

import androidx.lifecycle.ViewModel;

import com.shubhamgupta16.woomart.common.WooLiveData;
import com.shubhamgupta16.woomart.repo.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

import me.gilo.woodroid.models.Category;
import me.gilo.woodroid.models.filters.ProductCategoryFilter;


public final class CategoryViewModel extends ViewModel {
    private final CategoryRepository categoryRepository;

    @Inject
    CategoryViewModel(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }

    public WooLiveData<Category> create(Category category) {
        return categoryRepository.create(category);
    }


    public WooLiveData<Category> category(int id) {
        return categoryRepository.category(id);
    }

    public WooLiveData<List<Category>> categories() {
        return categoryRepository.categories();
    }

    public WooLiveData<List<Category>> categories(ProductCategoryFilter productCategoryFilter) {
        return categoryRepository.categories(productCategoryFilter);
    }

    public WooLiveData<Category> update(int id, Category category) {
        return categoryRepository.update(id, category);
    }

    public WooLiveData<Category> delete(int id) {
        return categoryRepository.delete(id);
    }

    public WooLiveData<Category> delete(int id, boolean force) {
        return categoryRepository.delete(id, force);
    }

}