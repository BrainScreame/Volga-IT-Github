package com.osenov.mygithub.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.osenov.mygithub.R
import com.osenov.mygithub.REPOSITORY
import com.osenov.mygithub.data.model.Repository
import com.osenov.mygithub.ui.base.BaseActivity
import com.osenov.mygithub.ui.detail_repository.DetailRepositoryActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        ButterKnife.bind(this)
        presenter.attachView(this)
    }

    override fun showError(message: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showRepositories(repositories: ArrayList<Repository>) {
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

    override fun onItemClickRepository(item: Repository, position: Int) {
        val intent = Intent(this, DetailRepositoryActivity::class.java)
        intent.putExtra(REPOSITORY, item);
        startActivity(intent)
    }

}