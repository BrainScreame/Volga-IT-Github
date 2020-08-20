package com.osenov.mygithub.di.component

import com.osenov.mygithub.di.module.ActivityModule
import com.osenov.mygithub.di.scope.PerActivity
import com.osenov.mygithub.ui.login.LoginActivity
import com.osenov.mygithub.ui.main.MainActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(mainActivity: MainActivity)
}