package com.osenov.mygithub.ui.login

import android.net.Uri
import com.osenov.mygithub.ApiClient
import com.osenov.mygithub.data.DataManager
import com.osenov.mygithub.di.scope.ConfigPersistent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class LoginPresenter
@Inject
constructor(private val dataManager: DataManager) : LoginContract.Presenter() {

    private var subscription: Disposable? = null

    override fun detachView() {
        super.detachView()
        subscription?.dispose()
    }

    override fun authorization(uri: Uri) {
        view.showProgress()
        val code = uri.getQueryParameter("code")

        subscription?.dispose()
        subscription = code?.let {
            dataManager.getLoginToken(it)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = { token ->
                        dataManager.preferencesHelper.setToken(token.accessToken)
                        ApiClient.token = token.accessToken
                        view.openMainActivity()
                    },
                    onError = {
                        view.showError()
                    }
                )
        }

    }

    override fun checkToken() {
        if (dataManager.preferencesHelper.getToken() != "") {
            ApiClient.token = dataManager.preferencesHelper.getToken()!!
            view.openMainActivity()
        }
    }

    override fun progressButtonClick() {
        view.openBrowser()
    }

}
