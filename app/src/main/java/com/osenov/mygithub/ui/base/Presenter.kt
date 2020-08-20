package com.osenov.mygithub.ui.base

interface Presenter<in V : MvpView> {
    fun attachView(view: V)
    fun detachView()
}
