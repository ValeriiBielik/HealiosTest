package com.sunnyday.healiostest.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class LifecycleDisposer {

    private val compositeDisposable = CompositeDisposable()

    fun bind(disposable: Disposable): Disposable {
        compositeDisposable.add(disposable)
        return disposable
    }

    fun disposeAll() {
        compositeDisposable.clear()
    }
}