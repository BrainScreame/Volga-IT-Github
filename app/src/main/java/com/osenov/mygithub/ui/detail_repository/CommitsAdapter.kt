package com.osenov.mygithub.ui.main

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
import com.osenov.mygithub.data.model.RepositoryMoreInfo
import com.osenov.mygithub.di.scope.ActivityContext
import com.osenov.mygithub.util.LanguageColorHelper
import de.hdodenhof.circleimageview.CircleImageView
import javax.inject.Inject


class RepositoriesAdapter
@Inject constructor(@ActivityContext private val context: Context) :
    RecyclerView.Adapter<RepositoriesAdapter.ViewHolderRepositories>() {

    private lateinit var repositories: ArrayList<Repository>
    private lateinit var clickListener: OnRepositoryItemClickListener

    fun setData(repositories: ArrayList<Repository>) {
        this.repositories = repositories
    }

    fun setItemRepository(index: Int, repository: Repository) {
        val diffPayLoad = Bundle()
        diffPayLoad.putParcelable(REPOSITORY_MORE_INFO, repository.repositoryMoreInfo)
        notifyItemChanged(index, diffPayLoad)
    }

    interface OnRepositoryItemClickListener {
        fun onItemClickRepository(item: Repository, position: Int)
    }

    fun setOnItemClickListener(listener: OnRepositoryItemClickListener) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRepositories {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_repository,
            parent,
            false
        )
        return ViewHolderRepositories(context, view)
    }

    override fun onBindViewHolder(
        holder: ViewHolderRepositories,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            val bundle = payloads[0] as Bundle
            val moreInfo: RepositoryMoreInfo? = bundle.getParcelable(REPOSITORY_MORE_INFO)
            if (moreInfo != null) {
                holder.bindMoreInfo(moreInfo)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolderRepositories, position: Int) {
        holder.bind(repositories[position], clickListener)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    class ViewHolderRepositories constructor(private val context: Context, itemView: View) :
        RecyclerView.ViewHolder(
            itemView
        ) {

        @BindView(R.id.textViewNameRepository)
        lateinit var textViewNameRepository: TextView

        @BindView(R.id.textViewDescriptionRepository)
        lateinit var textViewDescriptionRepository: TextView

        @BindView(R.id.textViewLanguage)
        lateinit var textViewLanguage: TextView

        @BindView(R.id.textViewStars)
        lateinit var textViewStars: TextView

        @BindView(R.id.textViewForks)
        lateinit var textViewForks: TextView

        @BindView(R.id.imageViewUserAvatar)
        lateinit var imageViewUserAvatar: CircleImageView

        @BindView(R.id.textViewUserName)
        lateinit var textViewUserName: TextView

        @BindView(R.id.linearLayoutMoreInfoState)
        lateinit var linearLayoutMoreInfoState: LinearLayout

        @BindView(R.id.linearLayoutMoreInfoLoad)
        lateinit var linearLayoutMoreInfoLoad: LinearLayout

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener {}
        }

        fun bindMoreInfo(moreInfo: RepositoryMoreInfo) {
            linearLayoutMoreInfoLoad.visibility = View.GONE
            linearLayoutMoreInfoState.visibility = View.VISIBLE

            if (moreInfo.language != null) {
                textViewLanguage.text = moreInfo.language

                val tintColor = LanguageColorHelper.getColor(context, moreInfo.language)
                var drawable = ContextCompat.getDrawable(context, R.drawable.ic_dot)
                if (drawable != null) {
                    drawable = DrawableCompat.wrap(drawable)
                    DrawableCompat.setTint(drawable.mutate(), tintColor!!)
                    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                }
                textViewLanguage.setCompoundDrawables(drawable, null, null, null)

            } else {
                textViewLanguage.visibility = View.GONE
            }
            textViewStars.text = moreInfo.stars
            textViewForks.text = moreInfo.forks
        }

        fun bind(repository: Repository, action: OnRepositoryItemClickListener) {
            linearLayoutMoreInfoState.visibility = View.GONE
            linearLayoutMoreInfoLoad.visibility = View.VISIBLE

            textViewNameRepository.text = repository.name

            if (repository.description == null) {
                textViewDescriptionRepository.visibility = View.GONE
            } else {
                textViewDescriptionRepository.text = repository.description
            }

            if (repository.repositoryMoreInfo != null) {
                bindMoreInfo(repository.repositoryMoreInfo!!)
            }

            Glide.with(itemView)
                .load(repository.owner.avatar)
                .into(imageViewUserAvatar)
            textViewUserName.text = repository.owner.name

            itemView.setOnClickListener {
                action.onItemClickRepository(repository, adapterPosition)
            }

        }
    }
}