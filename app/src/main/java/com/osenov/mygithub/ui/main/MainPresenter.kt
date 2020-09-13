package com.osenov.mygithub.ui.main

import com.osenov.mygithub.data.DataManager
import com.osenov.mygithub.data.model.Repository
import com.osenov.mygithub.di.scope.ConfigPersistent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.collections.ArrayList

@ConfigPersistent
class MainPresenter
@Inject
constructor(private val dataManager: DataManager) : MainContract.Presenter() {

    private var disposable: Disposable? = null
    private var repositories: ArrayList<Repository> = ArrayList()
    private var indexCounter = 0

    override fun attachView(view: MainContract.View) {
        super.attachView(view)
        if (repositories.size != 0) {
            view.showRepositories(repositories)
        } else {
            showRepositoryList()
        }
    }

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }

    override fun showRepositoryList() {
        disposable?.dispose()
        indexCounter = 0
        disposable = dataManager.getRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { repositories ->
                this.repositories = repositories
                view.showRepositories(repositories)
            }
            .doOnError {
                it.message?.let { it1 -> view.showError(it1) }
                disposable?.dispose()
            }
            .observeOn(Schedulers.io())
            .flatMap { repositories -> Observable.fromIterable(repositories) }
            .flatMap(
                { repository ->
                    dataManager.getRepositoryMoreInfo(
                        repository.owner.name,
                        repository.name
                    )
                },
                { repository, repositoryMoreInfo ->
                    repository.repositoryMoreInfo = repositoryMoreInfo
                    repository
                })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    repositories[indexCounter] = it
                    view.updateItemRepository(indexCounter, it)
                    indexCounter++
                },
                {
                    it.message?.let { it1 -> view.showError(it1) }
                    indexCounter = 0
                }
            )


    }

}
