package com.osenov.mygithub.ui.main

import com.osenov.mygithub.data.model.Repository
import com.osenov.mygithub.ui.base.BasePresenter
import com.osenov.mygithub.ui.base.MvpView

object MainContract {

    interface View : MvpView {
        fun showError(message: String)
        fun showRepositories(repositories: ArrayList<Repository>)
        fun updateItemRepository(index: Int, repository: Repository)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun showRepositoryList()
    }
}