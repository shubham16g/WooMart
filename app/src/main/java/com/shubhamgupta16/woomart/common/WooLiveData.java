package com.shubhamgupta16.woomart.common;

import androidx.lifecycle.LiveData;

import java.io.IOException;

import me.gilo.woodroid.callback.Resource;
import me.gilo.woodroid.callback.Status;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WooLiveData<T> extends LiveData<Resource<T>> implements Callback<T> {

    public WooLiveData() {
        setValue(new Resource<>(Status.LOADING));
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()){
            setValue(new Resource<>(response.body()));
        }else{
            String error = null;
            try {
                error = response.errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (error == null){
                error = "Something went wrong";
            }
            setValue(new Resource<>(new NetworkException(error)));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        setValue(new Resource<>( new NetworkException(t)));
    }
}
