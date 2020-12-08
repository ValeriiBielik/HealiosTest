package com.sunnyday.healiostest.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.sunnyday.healiostest.util.LifecycleDisposer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

open class BaseViewModel : ViewModel() {
    private val disposer: LifecycleDisposer = LifecycleDisposer()

    @CallSuper
    override fun onCleared() {
        disposer.disposeAll()
        super.onCleared()
    }

    fun <T> bind(
        observable: Observable<T>,
        onNext: Consumer<in T>?,
        onError: Consumer<in Throwable?>?
    ): Disposable {
        return disposer.bind(
            observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError)
        )
    }
}
