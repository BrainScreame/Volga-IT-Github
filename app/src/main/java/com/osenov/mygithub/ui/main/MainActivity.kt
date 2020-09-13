package com.osenov.mygithub.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.osenov.mygithub.R
import com.osenov.mygithub.REPOSITORY
import com.osenov.mygithub.data.model.Repository
import com.osenov.mygithub.ui.base.BaseActivity
import com.osenov.mygithub.ui.detail_repository.DetailRepositoryActivity
import com.osenov.mygithub.ui.login.LoginActivity
import javax.inject.Inject


class MainActivity : BaseActivity(), MainContract.View,
    RepositoriesAdapter.OnRepositoryItemClickListener {

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var repositoriesAdapter: RepositoriesAdapter

    @BindView(R.id.recyclerViewRepositories)
    lateinit var recyclerViewRepositories: RecyclerView

    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.swipeRefreshLayoutRepositories)
    lateinit var swipeRefreshLayoutRepositories: SwipeRefreshLayout

    @BindView(R.id.toolbarRepositories)
    lateinit var toolbarRepositories: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_main)
        init()
        swipeRefreshLayoutRepositories.setOnRefreshListener {
            presenter.showRepositoryList()
        }
    }

    private fun init() {
        ButterKnife.bind(this)
        presenter.attachView(this)
        setSupportActionBar(toolbarRepositories);
    }

    override fun showError(message: String) {
        swipeRefreshLayoutRepositories.isRefreshing = false
        progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showRepositories(repositories: ArrayList<Repository>) {
        swipeRefreshLayoutRepositories.isRefreshing = false
        progressBar.visibility = View.GONE
        recyclerViewRepositories.layoutManager = LinearLayoutManager(this)
        repositoriesAdapter.setData(repositories)
        repositoriesAdapter.setOnItemClickListener(this)
        repositoriesAdapter.notifyDataSetChanged()
        recyclerViewRepositories.adapter = repositoriesAdapter
    }

    override fun updateItemRepository(index: Int, repository: Repository) {
        repositoriesAdapter.setItemRepository(index, repository)
    }

    override fun showLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onItemClickRepository(item: Repository, position: Int) {
        val intent = Intent(this, DetailRepositoryActivity::class.java)
        intent.putExtra(REPOSITORY, item);
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_exit -> {
                presenter.exitAccount()
            }
        }
        return true
    }



}