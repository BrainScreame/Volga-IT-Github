package com.osenov.mygithub.ui.main

import com.osenov.mygithub.data.DataManager
import com.osenov.mygithub.di.scope.ConfigPersistent
import com.osenov.mygithub.ui.login.LoginContract
import javax.inject.Inject

@ConfigPersistent
class MainPresenter
@Inject
constructor(private val dataManager: DataManager) : MainContract.Presenter() {
}