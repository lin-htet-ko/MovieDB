package com.linhtetko.moviedb.domain.utils.extensions

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

fun <T : Any> Observable<T>.switchThread() = subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())