package com.osenov.mygithub.ui.detail_repository

import com.osenov.mygithub.data.DataManager
import com.osenov.mygithub.data.model.Repository
import com.osenov.mygithub.data.model.RepositoryCommit
import com.osenov.mygithub.di.scope.ConfigPersistent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class DetailRepositoryPresenter @Inject constructor(private val dataManager: DataManager) :
    DetailRepositoryContract.Presenter() {

    private var disposable: Disposable? = null
    private var commits: ArrayList<RepositoryCommit> = ArrayList()

    override fun loadCommits(ownerName: String, repositoryName: String) {
        if (commits.size == 0) {
            disposable?.dispose()
            disposable = dataManager.getCommits(ownerName, repositoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    commits = it
                    view.showCommits(it)
                },
                    {
                        view.showError(it.message.toString())
                    })
        } else {
            view.showCommits(commits)
        }

    }

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }

}