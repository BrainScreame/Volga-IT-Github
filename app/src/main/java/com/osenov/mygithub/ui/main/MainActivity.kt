package com.osenov.mygithub.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.apollographql.apollo.ApolloClient
import com.osenov.mygithub.R
import com.osenov.mygithub.data.model.Repository
import com.osenov.mygithub.ui.base.BaseActivity
import com.osenov.mygithub.ui.detail_repository.DetailRepositoryActivity
import com.osenov.mygithub.ui.login.LoginActivity
import kotlinx.coroutines.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainContract.View,
    RepositoriesAdapter.OnRepositoryItemClickListener {

    private val apolloClient = ApolloClient.builder()
        .serverUrl("https://api.github.com/graphql")
        .build()

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

    private val layoutManager by lazy(LazyThreadSafetyMode.NONE) { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_main)
        init()
        swipeRefreshLayoutRepositories.setOnRefreshListener {
            presenter.showRepositoryList()
        }


        val job = Job()
        val scope = CoroutineScope(job)

    }

    private fun init() {
        ButterKnife.bind(this)
        presenter.attachView(this)
        setSupportActionBar(toolbarRepositories);
        recyclerViewRepositories.layoutManager = layoutManager
        recyclerViewRepositories.adapter = repositoriesAdapter
        repositoriesAdapter.setOnItemClickListener(this)

    }

    override fun showError(message: String) {
        swipeRefreshLayoutRepositories.isRefreshing = false
        progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showRepositories(repositories: ArrayList<Repository>) {
        swipeRefreshLayoutRepositories.isRefreshing = false
        progressBar.visibility = View.GONE
        repositoriesAdapter.setData(repositories)

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
        intent.putExtra("REPOSITORY", item);
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_exit -> {
                presenter.exitAccount()
            }
        }
        return true
    }


}