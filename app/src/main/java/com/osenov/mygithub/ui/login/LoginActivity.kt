package com.osenov.mygithub.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.osenov.mygithub.CLIENT_ID
import com.osenov.mygithub.R
import com.osenov.mygithub.REDIRECT_URI
import com.osenov.mygithub.ui.base.BaseActivity
import com.osenov.mygithub.ui.main.MainActivity
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginPresenter

    @BindView(R.id.progressButtonWebLogin)
    lateinit var viewProgressButton: View

    private var progressButton: ProgressButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_login)
        init()
        presenter.checkToken()
    }

    override fun onResume() {
        super.onResume()
        val uri = intent.data
        if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
            presenter.authorization(uri)
        }
    }

    private fun init() {
        ButterKnife.bind(this)
        progressButton = ProgressButton(viewProgressButton)
        presenter.attachView(this)
    }

    @OnClick(R.id.progressButtonWebLogin)
    fun progressButtonClick() {
        presenter.progressButtonClick()
    }

    override fun openMainActivity() {
        progressButton?.buttonFinished()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun openBrowser() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&scope=repo&redirect_uri=$REDIRECT_URI")
        )
        startActivity(intent)
    }

    override fun showError() {
        Toast.makeText(this, R.string.error_get_token, Toast.LENGTH_LONG).show()
        progressButton?.buttonState()
    }

    override fun showProgress() {
        progressButton?.buttonActivated()
    }

    override fun hideProgress() {
        progressButton?.buttonState()
    }
}