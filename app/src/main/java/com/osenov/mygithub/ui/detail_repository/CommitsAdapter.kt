package com.osenov.mygithub.ui.detail_repository

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.osenov.mygithub.R
import com.osenov.mygithub.REPOSITORY_MORE_INFO
import com.osenov.mygithub.data.model.Repository
import com.osenov.mygithub.data.model.RepositoryCommit
import com.osenov.mygithub.data.model.RepositoryMoreInfo
import com.osenov.mygithub.di.scope.ActivityContext
import com.osenov.mygithub.util.LanguageColorHelper
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class CommitsAdapter
@Inject constructor() :
    RecyclerView.Adapter<CommitsAdapter.ViewHolderCommits>() {

    private lateinit var repositoryCommit: ArrayList<RepositoryCommit>
    private lateinit var clickListener: OnCommitItemClickListener

    fun setData(repositoryCommit: ArrayList<RepositoryCommit>) {
        this.repositoryCommit = repositoryCommit
    }


    interface OnCommitItemClickListener {
        fun onItemClickRepository(item: RepositoryCommit, position: Int)
    }

    fun setOnItemClickListener(listener: OnCommitItemClickListener) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCommits {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_commits,
            parent,
            false
        )
        return ViewHolderCommits(view)
    }

    override fun onBindViewHolder(holder: ViewHolderCommits, position: Int) {
        holder.bind(repositoryCommit[position], clickListener)
    }

    override fun getItemCount(): Int {
        return repositoryCommit.size
    }

    class ViewHolderCommits constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.textViewNameCommit)
        lateinit var textViewNameCommit: TextView

        @BindView(R.id.textViewDateCommit)
        lateinit var textViewDateCommit: TextView

        @BindView(R.id.imageViewUserAvatar)
        lateinit var imageViewUserAvatar: CircleImageView

        @BindView(R.id.textViewUserName)
        lateinit var textViewUserName: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(repositoryCommit: RepositoryCommit, action: OnCommitItemClickListener) {

            textViewNameCommit.text = repositoryCommit.commit.message
            textViewDateCommit.text =
                SimpleDateFormat("hh:mm:ss dd.MM.yyyy", Locale.getDefault())
                    .format(repositoryCommit.commit.committer.date)


            Glide.with(itemView)
                .load(repositoryCommit.author?.avatar)
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .into(imageViewUserAvatar)
            textViewUserName.text = repositoryCommit.commit.committer.name

            itemView.setOnClickListener {
                action.onItemClickRepository(repositoryCommit, adapterPosition)
            }

        }
    }
}