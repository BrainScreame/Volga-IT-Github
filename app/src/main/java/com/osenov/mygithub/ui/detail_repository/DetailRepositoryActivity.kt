package com.osenov.mygithub.ui.detail_repository

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.osenov.mygithub.R
import com.osenov.mygithub.REPOSITORY
import com.osenov.mygithub.data.model.Repository
import com.osenov.mygithub.data.model.RepositoryCommit
import com.osenov.mygithub.ui.base.BaseActivity
import javax.inject.Inject

class DetailRepositoryActivity : BaseActivity(), DetailRepositoryContract.View,
    CommitsAdapter.OnCommitItemClickListener {

    @Inject
    lateinit var presenter: DetailRepositoryPresenter

    @Inject
    lateinit var commitsAdapter: CommitsAdapter

    @BindView(R.id.toolbarDetailRepository)
    lateinit var toolbarDetailRepository: Toolbar

    @BindView(R.id.imageViewUserAvatar)
    lateinit var imageViewUserAvatar: ImageView

    @BindView(R.id.recyclerViewCommits)
    lateinit var recyclerViewCommits: RecyclerView

    @BindView(R.id.textViewNameRepository)
    lateinit var textViewNameRepository: TextView

    @BindView(R.id.progressBarCommits)
    lateinit var progressBarCommits: ProgressBar

    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setTheme(R.style.CollapsingTheme)
        setContentView(R.layout.activity_detail_repository)

        init()
    }

    private fun init() {
        ButterKnife.bind(this)
        presenter.attachView(this)

        toolbarDetailRepository.setNavigationOnClickListener {
            onBackPressed()
        }

        repository = intent.getParcelableExtra(REPOSITORY)!!

        Glide.with(this)
            .load(repository.owner.avatar)
            .into(imageViewUserAvatar)

        toolbarDetailRepository.title = repository.owner.name
        textViewNameRepository.text = repository.name
        presenter.loadCommits(repository.owner.name, repository.name)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showCommits(repositoryCommits: ArrayList<RepositoryCommit>) {
        progressBarCommits.visibility = View.GONE
        recyclerViewCommits.layoutManager = LinearLayoutManager(this)
        commitsAdapter.setData(repositoryCommits)
        commitsAdapter.notifyDataSetChanged()
        commitsAdapter.setOnItemClickListener(this)
        recyclerViewCommits.adapter = commitsAdapter
    }

    override fun onItemClickRepository(item: RepositoryCommit, position: Int) {
        Toast.makeText(this, item.commit.message, Toast.LENGTH_LONG).show()
    }

}