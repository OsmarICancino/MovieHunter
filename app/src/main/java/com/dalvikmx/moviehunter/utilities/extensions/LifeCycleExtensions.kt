package com.dalvikmx.moviehunter.utilities.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.dalvikmx.moviehunter.utilities.SingleEvent

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, { it?.let { t -> action(t) } })
}

fun <T> LifecycleOwner.observeEvent(liveData: LiveData<SingleEvent<T>>, action: (t: SingleEvent<T>) -> Unit) {
    liveData.observe(this, { it?.let { t -> action(t) } })
}

