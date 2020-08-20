package com.osenov.mygithub.ui.login

import android.net.Uri
import com.osenov.mygithub.ui.base.BasePresenter
import com.osenov.mygithub.ui.base.MvpView

object LoginContract {

    interface View : MvpView {
        fun openBrowser()
        fun openMainActivity()
        fun showError()
        fun showProgress()
        fun hideProgress()
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun authorization(uri: Uri)
        abstract fun checkToken()
        abstract fun progressButtonClick()
    }
}
