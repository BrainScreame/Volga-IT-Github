package com.osenov.mygithub.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import butterknife.ButterKnife
import com.osenov.mygithub.ApiClient
import com.osenov.mygithub.CLIENT_ID
import com.osenov.mygithub.R
import com.osenov.mygithub.REDIRECT_URI
import com.osenov.mygithub.ui.base.BaseActivity
import com.osenov.mygithub.ui.login.LoginContract
import com.osenov.mygithub.ui.login.LoginPresenter
import com.osenov.mygithub.ui.login.ProgressButton
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_main)
        init()

    }

    private fun init(){
        ButterKnife.bind(this)
        presenter.attachView(this)
    }

}