package com.osenov.mygithub.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.osenov.mygithub.REPOSITORY_MORE_INFO
import com.osenov.mygithub.data.model.Repository

abstract class RepositoriesDiffIUtil(
    oldRepositories: List<Repository>,
    newRepositories: List<Repository>
) : DiffUtil.Callback() {

    private val mOldRepositories: List<Repository> = oldRepositories
    private val mNewRepositories: List<Repository> = newRepositories

    override fun getOldListSize(): Int {
        return mOldRepositories.size
    }

    override fun getNewListSize(): Int {
        return mNewRepositories.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldRepositories[oldItemPosition].id == mNewRepositories[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldRepositories[oldItemPosition].repositoryMoreInfo == mNewRepositories[newItemPosition].repositoryMoreInfo
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {

        val diffPayLoad = Bundle()

        if(mOldRepositories[oldItemPosition].repositoryMoreInfo == mNewRepositories[newItemPosition].repositoryMoreInfo) {
            diffPayLoad.putParcelable(REPOSITORY_MORE_INFO, mNewRepositories[newItemPosition].repositoryMoreInfo)
        }

        return diffPayLoad
    }



}