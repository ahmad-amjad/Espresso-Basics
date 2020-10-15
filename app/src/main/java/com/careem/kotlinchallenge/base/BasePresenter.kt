package com.careem.kotlinchallenge.base

abstract class BasePresenter<T> {

    var view: T? = null

    open fun onCreate(view: T) {
        this.view = view
    }

    open fun onDestroy() {
        this.view = null
    }
}