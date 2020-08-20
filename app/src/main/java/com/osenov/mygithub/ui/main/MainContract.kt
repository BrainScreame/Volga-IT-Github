package com.osenov.mygithub.ui.main

import android.net.Uri
import com.osenov.mygithub.ui.base.BasePresenter
import com.osenov.mygithub.ui.base.MvpView

object MainContract {

    interface View : MvpView {

    }

    abstract class Presenter : BasePresenter<View>() {

    }
}