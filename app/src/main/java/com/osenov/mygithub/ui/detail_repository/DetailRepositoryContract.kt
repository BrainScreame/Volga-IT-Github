package com.osenov.mygithub.ui.detail_repository

import com.osenov.mygithub.data.model.Repository
import com.osenov.mygithub.data.model.RepositoryCommit
import com.osenov.mygithub.data.model.RepositoryMoreInfo
import com.osenov.mygithub.ui.base.BasePresenter
import com.osenov.mygithub.ui.base.MvpView

object DetailRepositoryContract {

    interface View : MvpView {
        fun showError(message: String)
        fun showCommits(repositoryCommits: ArrayList<RepositoryCommit>)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun loadCommits(ownerName: String, repositoryName: String)
    }

}