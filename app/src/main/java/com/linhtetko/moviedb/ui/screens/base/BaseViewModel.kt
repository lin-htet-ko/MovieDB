package com.linhtetko.moviedb.ui.screens.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {

    protected val cmp by lazy { CompositeDisposable() }

    var message by mutableStateOf<String?>(null)
        protected set

    var loading by mutableStateOf<Boolean>(false)
        protected set

    var error by mutableStateOf<String?>(null)
        protected set
}