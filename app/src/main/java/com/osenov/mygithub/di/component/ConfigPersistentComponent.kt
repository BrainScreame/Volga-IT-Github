package com.osenov.mygithub.di.component

import com.osenov.mygithub.di.module.ActivityModule
import com.osenov.mygithub.di.scope.ConfigPersistent
import dagger.Component

@ConfigPersistent
@Component(dependencies = [ApplicationComponent::class])
interface ConfigPersistentComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}
