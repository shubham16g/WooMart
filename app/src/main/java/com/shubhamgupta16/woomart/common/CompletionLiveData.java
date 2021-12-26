package com.shubhamgupta16.woomart.common;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import me.gilo.woodroid.callback.Resource;
import me.gilo.woodroid.callback.Status;


public final class CompletionLiveData extends LiveData<Resource<Boolean>> implements OnCompleteListener<Void> {


    public CompletionLiveData() {
        setValue(new Resource<>(Status.LOADING));
    }

    @Override
    public final void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()) {
            setValue(new Resource<>(true));
        } else {
            setValue(new Resource<>(task.getException()));
        }
    }
}
