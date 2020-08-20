package com.osenov.mygithub.di.module

import android.app.Activity
import android.content.Context
import com.osenov.mygithub.di.scope.ActivityContext
import com.osenov.mygithub.di.scope.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    internal fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @PerActivity
    @ActivityContext
    internal fun providesContext(): Context {
        return activity
    }

}