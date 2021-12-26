package com.shubhamgupta16.woomart.common

import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import me.gilo.woodroid.callback.Resource
import me.gilo.woodroid.callback.Status


class CompletionGenericLiveData<T> : LiveData<Resource<T>>(), OnCompleteListener<T> {
    init {
        value = Resource(Status.LOADING)
    }

    override fun onComplete(task: Task<T>) {
        if (task.isSuccessful) {
            setValue(Resource(task.result!!))
        } else {
            setValue(Resource(task.exception!!))
        }
    }
}

